package com.bt.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AllDepartments implements Serializable {
	private List<DepartmentBean> deptList;

	public AllDepartments() {
		deptList = new ArrayList<DepartmentBean>();
	}

	public void add(DepartmentBean bean) {
		deptList.add(bean);
	}

	public DepartmentBean get(int index) {
		return deptList.get(index);
	}

	public DepartmentBean get(String deptName) {
		DepartmentBean dept = null;
		for (DepartmentBean bean : deptList) {

			if (bean.getDepartmentName().equals(deptName)) {
				dept = bean;
				break;
			}
		}
		return dept;
	}

	public int getSize() {
		return deptList.size();
	}

	@Override
	public String toString() {
		return "AllDepartments{" + "deptList=" + deptList + '}';
	}
}
