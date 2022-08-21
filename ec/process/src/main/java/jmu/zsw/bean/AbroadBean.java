package jmu.zsw.bean;

import com.google.gson.internal.$Gson$Types;
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
public class AbroadBean implements Writable, DBWritable {
    private String datetime;
    private String provinceShortName;
    private Integer pid;
    private Integer confirmedCount;


    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(datetime);
        dataOutput.writeUTF(provinceShortName);
        dataOutput.writeInt(pid);
        dataOutput.writeInt(confirmedCount);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.datetime = dataInput.readUTF();
        this.provinceShortName = dataInput.readUTF();
        this.pid = dataInput.readInt();
        this.confirmedCount = dataInput.readInt();
    }

    @Override
    public void write(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1,datetime);
        preparedStatement.setString(2,provinceShortName);
        preparedStatement.setInt(3,pid);
        preparedStatement.setInt(4,confirmedCount);
    }

    @Override
    public void readFields(ResultSet resultSet) throws SQLException {
        this.datetime = resultSet.getString(1);
        this.provinceShortName = resultSet.getString(2);
        this.pid = resultSet.getInt(3);
        this.confirmedCount = resultSet.getInt(4);
    }
}
