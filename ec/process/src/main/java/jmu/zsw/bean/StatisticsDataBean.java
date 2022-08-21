package jmu.zsw.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatisticsDataBean implements Writable {
    private String dateId;
    private String provinceShortName;
    private Integer locationId;
    private Integer confirmedCount;
    private Integer confirmedIncr;
    private Integer curedCount;
    private Integer curedIncr;
    private Integer currentConfirmedCount;
    private Integer currentConfirmedIncr;
    private Integer deadCount;
    private Integer deadIncr;
    private Integer suspectedCount;
    private Integer suspectedCountIncr;

    public StatisticsDataBean(Integer confirmedIncr, Integer confirmedCount, Integer suspectedCount, Integer curedCount, Integer deadCount) {
        this.confirmedIncr = confirmedIncr;
        this.confirmedCount = confirmedCount;
        this.suspectedCount = suspectedCount;
        this.curedCount = curedCount;
        this.deadCount = deadCount;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(confirmedIncr);
        dataOutput.writeInt(confirmedCount);
        dataOutput.writeInt(suspectedCount);
        dataOutput.writeInt(curedCount);
        dataOutput.writeInt(deadCount);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.confirmedIncr = dataInput.readInt();
        this.confirmedCount = dataInput.readInt();
        this.suspectedCount = dataInput.readInt();
        this.curedCount = dataInput.readInt();
        this.deadCount = dataInput.readInt();
    }

    @Override
    public String toString() {
        return dateId+"\t"+confirmedIncr+"\t"+confirmedCount+"\t"+
                suspectedCount+"\t"+curedCount+"\t"+deadCount;
    }
}
