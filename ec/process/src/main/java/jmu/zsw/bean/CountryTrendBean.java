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
public class CountryTrendBean implements Writable, DBWritable {
    private String dateId;
    private Integer confirmedIncr;
    private Integer confirmedCount;
    private Integer suspectedCount;
    private Integer curedCount;
    private Integer deadCount;


    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(dateId);
        dataOutput.writeInt(confirmedIncr);
        dataOutput.writeInt(confirmedCount);
        dataOutput.writeInt(suspectedCount);
        dataOutput.writeInt(curedCount);
        dataOutput.writeInt(deadCount);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.dateId = dataInput.readUTF();
        this.confirmedIncr = dataInput.readInt();
        this.confirmedCount = dataInput.readInt();
        this.suspectedCount = dataInput.readInt();
        this.curedCount = dataInput.readInt();
        this.deadCount = dataInput.readInt();
    }

    @Override
    public void write(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, dateId);
        preparedStatement.setInt(2,confirmedIncr);
        preparedStatement.setInt(3,confirmedCount);
        preparedStatement.setInt(4,suspectedCount);
        preparedStatement.setInt(5,curedCount);
        preparedStatement.setInt(6,deadCount);
    }

    @Override
    public void readFields(ResultSet resultSet) throws SQLException {
        this.dateId = resultSet.getString(1);
        this.confirmedIncr = resultSet.getInt(2);
        this.confirmedCount = resultSet.getInt(3);
        this.suspectedCount = resultSet.getInt(4);
        this.curedCount = resultSet.getInt(5);
        this.deadCount = resultSet.getInt(6);
    }

    @Override
    public String toString() {
        return dateId+"\t"+confirmedIncr+"\t"+confirmedCount+"\t"+
                suspectedCount+"\t"+curedCount+"\t"+deadCount;
    }
}
