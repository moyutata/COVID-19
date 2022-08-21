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

/*
    * 全国疫情统计数据
    * */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryBean implements Writable, DBWritable {
    private String datetime;
    private Integer currentConfirmedCount;
    private Integer confirmedCount;
    private Integer curedCount;
    private Integer deadCount;

    /*
    * 序列化方法*/
    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(datetime);
        dataOutput.writeInt(confirmedCount);
        dataOutput.writeInt(curedCount);
        dataOutput.writeInt(currentConfirmedCount);
        dataOutput.writeInt(deadCount);
    }

    /*
    * 反序列化方法*/
    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.datetime = dataInput.readUTF();
        this.confirmedCount = dataInput.readInt();
        this.curedCount = dataInput.readInt();
        this.currentConfirmedCount = dataInput.readInt();
        this.deadCount = dataInput.readInt();
    }

    /*
    * 在preparedStatement中设置对象的字段*/
    @Override
    public void write(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1,datetime);
        preparedStatement.setInt(2,currentConfirmedCount);
        preparedStatement.setInt(3,confirmedCount);
        preparedStatement.setInt(4,curedCount);
        preparedStatement.setInt(5,deadCount);
    }

    /*
    * 从ResultSet中读取对象的字段*/
    @Override
    public void readFields(ResultSet resultSet) throws SQLException {
        this.datetime = resultSet.getString(1);
        this.currentConfirmedCount = resultSet.getInt(2);
        this.confirmedCount = resultSet.getInt(3);
        this.curedCount = resultSet.getInt(4);
        this.deadCount = resultSet.getInt(5);
    }

    @Override
    public String toString() {
        return datetime+"\t"+currentConfirmedCount+"\t"+confirmedCount+"\t"
                +curedCount+"\t"+deadCount;
    }
}
