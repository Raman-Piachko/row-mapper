package com.epam.rd.autocode;

import com.epam.rd.autocode.domain.Employee;
import com.epam.rd.autocode.domain.FullName;
import com.epam.rd.autocode.domain.Position;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;


public class RowMapperImpl implements RowMapper {
    public static final Logger LOGGER = LogManager.getLogger(RowMapperImpl.class);

    @Override
    public Object mapRow(ResultSet resultSet) {
        Employee employee = null;
        try {
            employee = new Employee(
                    new BigInteger(String.valueOf(resultSet.getInt("id"))),
                    new FullName(
                            resultSet.getString("firstname"),
                            resultSet.getString("lastname"),
                            resultSet.getString("middleName")),
                    Position.valueOf(resultSet.getString("position")),
                    resultSet.getDate("hiredate").toLocalDate(),
                    resultSet.getBigDecimal("salary"));
        } catch (SQLException e) {
            LOGGER.error("Something went wrong at mapRow!!!", e);
        }

        return employee;
    }
}
