package iec61850.nodes.custom;

import iec61850.nodes.common.LN;
import iec61850.objects.samples.SAV;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Узел считывания файла CSV
 *
 * @see SAV - представленние мгновенных значений
 */
public class LSVC extends LN {

    private List<SAV> signals = new ArrayList<>(); //Список считываемых из строки файла значений
    private List<String> cfgFile = new ArrayList<>(); //Считанный файл конфиг
    private List<String> csvFile = new ArrayList<>(); //Считанный файл CSV
    private int numberOfSignals; //Количество аналоговых и дискретных значений
    private Iterator<String> iterator; //Итератор для перебора значений и последовательной обработки всеми узлами

    /**
     * Последовательное считывание значений из файла
     * И обертывание значений
     */
    @Override
    public void process() {
        if (iterator.hasNext()) {
            List<Float> values = Arrays.stream(iterator.next().split(","))
                    .map(Float::parseFloat)
                    .collect(Collectors.toList());
            for (int i = 0; i < numberOfSignals; i++) {
                signals.get(i)
                        .getInstMag()
                        .getF()
                        .setValue(values.get(i + 1) * 1000);
            }
        }

    }

    /**
     * Полное считывание файлов конфиг и CSV
     * Определение итератора для прохождения по прочитанному файлу
     * Определение количества аналоговых и дискретных сигналов
     * Формирование списка мгновенных значений
     *
     * @param cfgPath - путь к файлу без расширения файла конфиг и дата файл должны лежать вместе
     */
    public void readCSV(String cfgPath) {
        cfgFile = readFile(cfgPath + ".cfg");
        csvFile = readFile(cfgPath + ".csv");

        iterator = csvFile.iterator();

        int analogNumber = Integer.parseInt(cfgFile.get(1).split(",")[1].replace("A", ""));
        int discreteNumber = Integer.parseInt(cfgFile.get(1).split(",")[2].replace("D", ""));
        numberOfSignals = analogNumber + discreteNumber;
        while (signals.size() < numberOfSignals) {
            signals.add(new SAV());
        }
        System.out.printf("Осциллограмма загружена, количество сигналов: %s, количество выборок: %s %n%n", numberOfSignals, csvFile.size());
    }

    /**
     * Запись передаваемого файла в список
     *
     * @param path - путь к файлу
     * @return - список всех строк из файла
     */
    private List<String> readFile(String path) {
        List<String> fileEntry = new ArrayList<>();
        try (Stream<String> lines = Files.newBufferedReader(Paths.get(path)).lines()) {
            fileEntry = lines.filter(i -> !i.equals("\"Time\",\"Subsystem #1|Branch Currents|IaLine\",\"Subsystem #1|Branch Currents|IbLine\",\"Subsystem #1|Branch Currents|IcLine\",\"Subsystem #1|CTLs|Inputs|LG_FLT\""))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла");
        }
        return fileEntry;
    }

    public boolean hasNext() {
        return iterator.hasNext();
    } //проверка на наличие следующего сигнала в итераторе

    public List<SAV> getSignals() {
        return signals;
    }
}
