package jmu.zsw.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.lib.db.DBWritable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProvinceBean implements Writable, DBWritable {
    private String datetime;
    private Integer locationId;
    private String provinceShortName;       //省份短名
    private Integer currentConfirmedCount;   //当前确诊人数
    private Integer confirmedCount;          //累计确诊人数
    private Integer suspectedCount;          //疑似病例人数
    private Integer curedCount;              //治愈人数
    private Integer deadCount;               //死亡人数
    private String statisticsData;

    public ProvinceBean(String datetime, Integer locationId, String provinceShortName, Integer currentConfirmedCount, Integer confirmedCount, Integer suspectedCount, Integer curedCount, Integer deadCount) {
        this.datetime = datetime;
        this.locationId = locationId;
        this.provinceShortName = provinceShortName;
        this.currentConfirmedCount = currentConfirmedCount;
        this.confirmedCount = confirmedCount;
        this.suspectedCount = suspectedCount;
        this.curedCount = curedCount;
        this.deadCount = deadCount;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(datetime);
        dataOutput.writeInt(locationId);
        dataOutput.writeUTF(provinceShortName);
        dataOutput.writeInt(currentConfirmedCount);
        dataOutput.writeInt(confirmedCount);
        dataOutput.writeInt(suspectedCount);
        dataOutput.writeInt(curedCount);
        dataOutput.writeInt(deadCount);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.datetime = dataInput.readUTF();
        this.locationId = dataInput.readInt();
        this.provinceShortName = dataInput.readUTF();
        this.currentConfirmedCount = dataInput.readInt();
        this.confirmedCount = dataInput.readInt();
        this.suspectedCount = dataInput.readInt();
        this.curedCount = dataInput.readInt();
        this.deadCount = dataInput.readInt();
    }

    @Override
    public void write(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1,datetime);
        preparedStatement.setInt(2,locationId);
        preparedStatement.setString(3,provinceShortName);
        preparedStatement.setInt(4,currentConfirmedCount);
        preparedStatement.setInt(5,confirmedCount);
        preparedStatement.setInt(6,suspectedCount);
        preparedStatement.setInt(7,curedCount);
        preparedStatement.setInt(8,deadCount);
    }

    @Override
    public void readFields(ResultSet resultSet) throws SQLException {
        this.datetime = resultSet.getString(1);
        this.locationId = resultSet.getInt(2);
        this.provinceShortName = resultSet.getString(3);
        this.currentConfirmedCount = resultSet.getInt(4);
        this.confirmedCount = resultSet.getInt(5);
        this.suspectedCount = resultSet.getInt(6);
        this.curedCount = resultSet.getInt(7);
        this.deadCount = resultSet.getInt(8);
    }

    @Override
    public String toString() {
        return datetime+"\t"+locationId+"\t"+provinceShortName+"\t"+
                currentConfirmedCount+"\t"+confirmedCount+"\t"+
                suspectedCount+"\t"+curedCount+"\t"+deadCount;
    }
}
