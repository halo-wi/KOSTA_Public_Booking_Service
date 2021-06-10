package com.kosta.sbproject.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.kosta.sbproject.model.PDSBoard;

public interface PDSBoadRepository extends CrudRepository<PDSBoard, Long>{
	//JPQL문법 : entity이름에 주의한다.
	@Query("select b.pid, count(f) "
			+ "from PDSBoard b left outer join b.file2 f "
			+ "group by b.pid")
	public List<Object[]>getBoardWithFileCount2();

	@Query("select b.pid, b.pname, b.pwriter, f.pdsfile, f.fno "
			+ "from PDSBoard b left outer join b.file2 f ")
	public List<Object[]>getBoardWithFileCount3();
	
	@Modifying
	@Query("update from PDSFile f set f.pdsfile = ?2 where f.fno = ?1")
	public int updatePdsFile2(Long fno, String filename);
	
	
}
