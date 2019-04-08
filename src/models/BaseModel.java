package models;

import entities.Account;
import utils.ConnectionManager;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;

public abstract class BaseModel<T> {
    private Class<T> clazz;

    void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }

    public List<T> getList() throws SQLException, IllegalAccessException, InstantiationException {
        Connection conn = ConnectionManager.getConnection();
        Statement stmt = conn.createStatement();

        String tableName = clazz.getSimpleName().toLowerCase();

        ResultSet rs = stmt.executeQuery("select * from " + tableName);
        return resultSetToList(rs);
    }

    public T insertOne(T instance) throws SQLException, IllegalAccessException, InstantiationException {
        Connection conn = ConnectionManager.getConnection();
        String tableName = clazz.getSimpleName().toLowerCase();
        StringBuilder querySb = new StringBuilder();
        StringBuilder valueSb = new StringBuilder();

        querySb.append("insert into ")
                .append(tableName)
                .append("(");
        valueSb.append(" values(");
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            String fieldName = field.getName();
            querySb.append(fieldName);
            valueSb.append("?");
            if (i < fields.length - 1) {
                querySb.append(",");
                valueSb.append(",");
            }
        }
        querySb.append(")");
        valueSb.append(")");
        querySb.append(valueSb);
        PreparedStatement stmt = conn.prepareStatement(querySb.toString(), Statement.RETURN_GENERATED_KEYS);

        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            stmt.setObject(i + 1, field.get(instance));
        }

        T result = null;
        System.out.println(querySb.toString());
        if (stmt.executeUpdate() > 0) {
            ResultSet generated = stmt.getGeneratedKeys();
            if ( generated.next() ) {
                int id = generated.getInt(1);
                result = findById(id);
            }
        }
        conn.close();
        return result;
    }

    public T findById(int id) throws SQLException, InstantiationException, IllegalAccessException {
        Connection conn = ConnectionManager.getConnection();
        String tableName = clazz.getSimpleName().toLowerCase();
        StringBuilder querySb = new StringBuilder();
        querySb.append("select * from ")
                .append(tableName)
                .append(" where id=?");
        PreparedStatement stmt = conn.prepareStatement(querySb.toString());
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        List<T> listResult = resultSetToList(rs);
        if (listResult.size() > 0) {
            return listResult.get(0);
        } else {
            return null;
        }

    }

    public List<T> findByConditions(Map<String, Object> conditions) throws SQLException, InstantiationException, IllegalAccessException {
        Connection conn = ConnectionManager.getConnection();
        String tableName = clazz.getSimpleName().toLowerCase();
        StringBuilder querySb = new StringBuilder();
        querySb.append("select * from ")
                .append(tableName)
                .append(" where ");
        Set<String> keySet = conditions.keySet();
        for (String key: conditions.keySet()) {
            querySb
                    .append(key)
                    .append("=?");
        }

        PreparedStatement stmt = conn.prepareStatement(querySb.toString());
        Object[] keyArray = keySet.toArray();
        for (int i = 0; i < keySet.size(); i++) {
            stmt.setObject(i + 1, conditions.get(keyArray[i]));
        }
        ResultSet rs = stmt.executeQuery();

        return resultSetToList(rs);
    }

    List<T> resultSetToList(ResultSet rs) throws SQLException, IllegalAccessException, InstantiationException {
        List<T> result = new ArrayList<>();
        while (rs.next()) {
            Field[] fields = clazz.getDeclaredFields();
            T instance = clazz.newInstance();
            for (Field field: fields) {
                field.setAccessible(true);
                field.set(instance, rs.getObject(field.getName(), field.getType()));
            }
            result.add(instance);
        }
        return result;
    }

    public boolean removeById(int id) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        String tableName = clazz.getSimpleName().toLowerCase();
        StringBuilder querySb = new StringBuilder();
        querySb.append("delete from ")
                .append(tableName)
                .append(" where id=?");
        PreparedStatement stmt = conn.prepareStatement(querySb.toString());
        stmt.setInt(1, id);
        return stmt.execute();
    }
}
