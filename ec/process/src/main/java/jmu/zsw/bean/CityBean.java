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
@NoArgsConstructor
@AllArgsConstructor
public class CityBean implements Writable, DBWritable {
    private String datetime;
    private String provinceShortName;
    private Integer pid;
    private String cityName;
    private Integer locationId;
    private Integer confirmedCount;
    private Integer curedCount;
    private Integer currentConfirmedCount;
    private Integer deadCount;
    private Integer suspectedCount;


    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(datetime);
        dataOutput.writeUTF(provinceShortName);
        dataOutput.writeInt(pid);
        dataOutput.writeUTF(cityName);
        dataOutput.writeInt(locationId);
        dataOutput.writeInt(confirmedCount);
        dataOutput.writeInt(curedCount);
        dataOutput.writeInt(currentConfirmedCount);
        dataOutput.writeInt(deadCount);
        dataOutput.writeInt(suspectedCount);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.datetime = dataInput.readUTF();
        this.provinceShortName = dataInput.readUTF();
        this.pid = dataInput.readInt();
        this.cityName = dataInput.readUTF();
        this.locationId = dataInput.readInt();
        this.confirmedCount = dataInput.readInt();
        this.curedCount = dataInput.readInt();
        this.currentConfirmedCount = dataInput.readInt();
        this.deadCount = dataInput.readInt();
        this.suspectedCount = dataInput.readInt();
    }

    @Override
    public void write(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1,datetime);
        preparedStatement.setInt(2,pid);
        preparedStatement.setString(3,provinceShortName);
        preparedStatement.setInt(4,locationId);
        preparedStatement.setString(5,cityName);
        preparedStatement.setInt(6,currentConfirmedCount);
        preparedStatement.setInt(7,confirmedCount);
        preparedStatement.setInt(8,suspectedCount);
        preparedStatement.setInt(9,curedCount);
        preparedStatement.setInt(10,deadCount);
    }

    @Override
    public void readFields(ResultSet resultSet) throws SQLException {
        this.datetime = resultSet.getString(1);
        this.pid = resultSet.getInt(2);
        this.provinceShortName = resultSet.getString(3);
        this.locationId = resultSet.getInt(4);
        this.cityName = resultSet.getString(5);
        this.currentConfirmedCount = resultSet.getInt(6);
        this.confirmedCount = resultSet.getInt(7);
        this.suspectedCount = resultSet.getInt(8);
        this.curedCount = resultSet.getInt(9);
        this.deadCount = resultSet.getInt(10);
    }

    @Override
    public String toString() {
        return datetime+"\t"+provinceShortName+"\t"+pid+"\t"+
                cityName+"\t"+locationId+"\t"+confirmedCount+"\t"+
                curedCount+"\t"+currentConfirmedCount+"\t"+
                deadCount+"\t"+suspectedCount;
    }
}
