package net.codejava;
import java.sql.Connection;
import java.util.List;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface Controllers <T>{
public List<?> list();
public ResponseEntity<?> get(Integer id);
public void delete(Integer id);
void addSign(T obj);
ResponseEntity<?> update(T obj, Integer id);
}


