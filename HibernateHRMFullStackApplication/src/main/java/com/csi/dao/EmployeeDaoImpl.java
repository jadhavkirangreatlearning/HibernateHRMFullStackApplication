package com.csi.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import com.csi.model.Employee;

public class EmployeeDaoImpl implements EmployeeDao {

	private static SessionFactory factory = new AnnotationConfiguration().configure().buildSessionFactory();

	@Override
	public void signUp(Employee employee) {
		// TODO Auto-generated method stub

		Session session = factory.openSession();

		Transaction transaction = session.beginTransaction();

		session.save(employee);

		transaction.commit();
	}

	@Override
	public boolean signIn(String empEmailId, String empPassword) {
		// TODO Auto-generated method stub

		boolean flag = false;

		for (Employee employee : getAllData()) {
			if (employee.getEmpEmailId().equals(empEmailId) && employee.getEmpPassword().equals(empPassword)) {
				flag = true;
				break;
			}
		}

		return flag;
	}

	@Override
	public Employee getDataById(int empId) {
		// TODO Auto-generated method stub

		Session session = factory.openSession();

		Employee employee = (Employee) session.get(Employee.class, empId);
		return employee;
	}

	@Override
	public List<Employee> getAllData() {
		// TODO Auto-generated method stub

		Session session = factory.openSession();

		List<Employee> employees = session.createQuery("from Employee").list();

		return employees;
	}

	@Override
	public void updateData(int empId, Employee employee) {
		// TODO Auto-generated method stub

		Session session = factory.openSession();

		Transaction transaction = session.beginTransaction();

		for (Employee employee2 : getAllData()) {
			if (employee2.getEmpId() == empId) {
				employee2.setEmpAddress(employee.getEmpAddress());
				employee2.setEmpAge(employee.getEmpAge());
				employee2.setEmpContactNumber(employee.getEmpContactNumber());
				employee2.setEmpDOB(employee.getEmpDOB());
				employee2.setEmpEmailId(employee.getEmpEmailId());
				employee2.setEmpGender(employee.getEmpGender());
				employee2.setEmpName(employee.getEmpName());
				employee2.setEmpPanCardNumber(employee.getEmpPanCardNumber());
				employee2.setEmpUID(employee.getEmpUID());
				employee2.setEmpSalary(employee.getEmpSalary());

				session.update(employee2);
				transaction.commit();
			}
		}
	}

	@Override
	public void deleteDataById(int empId) {
		// TODO Auto-generated method stub

		Session session = factory.openSession();

		Transaction transaction = session.beginTransaction();

		for (Employee employee2 : getAllData()) {
			if (employee2.getEmpId() == empId) {

				session.delete(employee2);
				transaction.commit();
			}
		}

	}

	@Override
	public void deleteAllData() {
		// TODO Auto-generated method stub

		Session session = factory.openSession();

		for (Employee employee : getAllData()) {
			Transaction transaction = session.beginTransaction();

			session.delete(employee);

			transaction.commit();
		}
	}

}
