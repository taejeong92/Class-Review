package org.doit.ik;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.doit.ik.domain.DeptDTO;
import org.doit.ik.domain.EmpDTO;
import org.doit.ik.mapper.TimeMapper;
import org.doit.ik.mapper.scott.DeptMapper;
import org.doit.ik.mapper.scott.EmpMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.Setter;

@Controller
public class ScottController {

	// DI
	// @Autowired
	@Setter(onMethod=@__({@Autowired}))
	private DeptMapper deptMapper;
	
	@Setter(onMethod=@__({@Autowired}))
	private EmpMapper empMapper;

	private static final Logger logger = LoggerFactory.getLogger(TimeMybatisController.class);

	@GetMapping(value = "/scott/empdept")   // 
	public String empdept(HttpServletRequest request
			, @RequestParam( value = "deptno" , defaultValue = "10") int deptno) { 
		logger.info("> ScottController.empdept() 컨트롤러 메서드 호출됨...");
		
		// [1]
		ArrayList<DeptDTO> dlist = this.deptMapper.selectDept();
		request.setAttribute("dlist", dlist);
		// [2]
        ArrayList<EmpDTO> elist =  this.empMapper.selectEmpDept(deptno);		
		request.setAttribute("elist", elist);
		
		return "/scott/empdept";
	}
	
	//    /scott/dept 요청 -> 컨트롤러 메서드 선언
	// @RequestMapping(value = "/scott/dept", method = RequestMethod.GET)
	@GetMapping(value = "/scott/dept")
	public String dept(HttpServletRequest request) {

		logger.info("> ScottController.dept() 컨트롤러 메서드 호출됨...");
		ArrayList<DeptDTO> list = this.deptMapper.selectDept();
		request.setAttribute("list", list);
		return "/scott/dept";
	}


	// 부서원 검색하는 컨트롤러 메서드 선언
	// deptno=10&deptno=20
	// @RequestMapping(value = "/scott/emp", method = RequestMethod.POST)
	@PostMapping(value = "/scott/emp")
	public String emp(HttpServletRequest request
			, @RequestParam("deptno") int [] deptnoArr ) {

		logger.info("> ScottController.emp() 컨트롤러 메서드 호출됨...");
 
		// [1] JSP 
		/*
		// deptno=10&deptno=20
		int [] deptnoArr = null;
		String [] pDeptnoArr = request.getParameterValues("deptno");
		deptnoArr = new int[ pDeptnoArr.length ];
		for (int i = 0; i < pDeptnoArr.length; i++) {
			deptnoArr[i] = Integer.parseInt(  pDeptnoArr[i]  );
		} // for
		*/
		
		//
		// ArrayList<EmpDTO> list =  this.empMapper.selectEmp();
		ArrayList<EmpDTO> list =  this.empMapper.selectEmp(deptnoArr);
		
		request.setAttribute("list", list);
		
		// [/WEB-INF/views     /scott/emp    .jsp]
		return "/scott/emp";
	}

} // class


/*
 * HTTP 상태 400 – 잘못된 요청
 * 메시지 Required int parameter 'deptno' is not present
 * 
 *  * 
-- ScottController.java

@RequestMapping(value = "/scott/empdept")
public String empdept(HttpServletRequest request
                 , @RequestParam(value = "deptno", required = false) Integer deptno) {
   logger.info("> ScottController.empdept() 컨트롤러 메서드 호출됨...");
   
   ArrayList<DeptDTO> deptList = this.deptMapper.selectDept();
   
   ArrayList<EmpDTO> empList = null;
   if (deptno == null) {
      empList = this.empMapper.selectAllEmp();
   } else {
      empList = this.empMapper.selectEmpByDeptno(deptno.intValue());
   }
   
   request.setAttribute("deptList", deptList);
   request.setAttribute("empList", empList);
   
   return "scott/empdept";
}
 

-- EmpMapper.java

public interface EmpMapper {

ArrayList<EmpDTO> selectAllEmp();

ArrayList<EmpDTO> selectEmpByDeptnoList(int[] deptnoArr);

ArrayList<EmpDTO> selectEmpByDeptno(int deptno);

}


-- EmpMapper.xml
<mapper namespace="org.doit.ik.mapper.scott.EmpMapper">

 <select id="selectAllEmp" resultType="org.doit.ik.domain.EmpDTO">
      SELECT *
      FROM emp
      ORDER BY deptno ASC
</select>
   
<select id="selectEmpByDeptnoList" resultType="org.doit.ik.domain.EmpDTO">
      SELECT empno, ename, job, mgr, hiredate, sal, comm, deptno
      FROM emp
<where>
    <foreach item="deptno" collection="array"
        open="deptno in (" separator="," close=")">
          #{deptno}
    </foreach>
</where>
</select>

<select id="selectEmpByDeptno" parameterType="int" resultType="org.doit.ik.domain.EmpDTO">
      SELECT empno, ename, job, mgr, hiredate, sal, comm, deptno
      FROM emp
      WHERE deptno = ${_parameter}
</select>

</mapper>
*/




