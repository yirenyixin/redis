package jmu.dao;

import jmu.model.Indent;

import java.util.List;

public interface IIndentDAO {
   List<Indent> queryAll0(String id);

   void updateAffirm(String id);
}
