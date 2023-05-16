package jmu.dao;

import jmu.model.Reports;

import java.util.List;

public interface IReportsDAO {
    public List<Reports> report(String id, String flag)throws Exception;
}
