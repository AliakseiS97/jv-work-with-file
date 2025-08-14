package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private int supply = 0;
    private int buy = 0;

    public int getSupply() {
        return supply;
    }

    public void setSupply(int supply) {
        this.supply = supply;
    }

    public int getBuy() {
        return buy;
    }

    public void setBuy(int buy) {
        this.buy = buy;
    }

    public void getStatistic(String fromFileName, String toFileName) {
        this.supply = 0;
        this.buy = 0;
        readFile(fromFileName);
        writeFile(toFileName);
    }

    public void readFile(String fromFileName) {
        File file = new File(fromFileName);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] array = line.split(",");
                if (array[0].equals("supply")) {
                    supply += Integer.parseInt(array[1].trim().replace("]",""));
                } else if (array[0].equals("buy")) {
                    buy += Integer.parseInt(array[1].trim().replace("]",""));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }
    }

    private int getResult() {
        return supply - buy;
    }

    private void writeFile(String toFileName) {
        try (FileWriter writer = new FileWriter(toFileName)) {
            writer.write("supply," + supply + System.lineSeparator());
            writer.write("buy," + buy + System.lineSeparator());
            writer.write("result," + getResult() + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
