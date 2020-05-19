package cb.lms.CB_Lms.modal;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author 1595812
 *
 */
@Entity
@Table(name = "GA_FACULTY")
@NamedQueries({ @NamedQuery(name = "getAllUsers", query = "from Faculty user "),
		@NamedQuery(name = "getUserByEmailId", query = "from Faculty user  where user.email=:emailId"),
		@NamedQuery(name = "retrieveUserByCredentials", query = "from Faculty user left join fetch user.loginAccount loginAccount where loginAccount.userName=:userName and loginAccount.password=:password and loginAccount.status=1 "), })
public class Faculty implements IDataEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;

	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;

	@Column(name = "MIDDLE_NAME", nullable = true)
	private String middleName;

	@Column(name = "LAST_NAME", nullable = false)
	private String lastName;

	@Column(name = "GENDER", nullable = false)
	private String gender;

	@Column(name = "MOBILE", nullable = false)
	private String mobile;

	@Column(name = "EMAIL", nullable = false)
	private String email;

	@Column(name = "DOB", nullable = false)
	private String dob;

	@Column(name = "PAN", nullable = false)
	private String pan;

	@Column(name = "DESIGNATION", nullable = false)
	private String designation;

	@Column(name = "QUALIFICATION", nullable = false)
	private String qualification;

	@Column(name = "EMPLOYMENT_TYPE", nullable = false)
	private String employmentType;

	@Column(name = "STATUS", nullable = false)
	private String status;

	@Column(name = "PRM_ADDR1")
	String addrLine1;

	@Column(name = "PRM_ADDR2")
	String addrLine2;

	@Column(name = "PRM_ADDR_CITY")
	String city;

	@Column(name = "PRM_ADDR_STATE")
	String state;

	@Column(name = "PRM_ADDR_COUNTRY")
	String country;

	@Column(name = "PRM_ADDR_ZIP")
	String zipCode;
	
	@Column(name = "IS_SUPERVISOR")
	String isSupervisor;
	
	@Column(name = "SUPERVISOR_ID")
	Integer supervisor;

	@OneToOne
	@JoinColumn(name = "ROLE_ID")
	private Role role;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "login_account_id")
	//@JsonIgnore
	public LoginAccount loginAccount;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "FACULTY_ACADEMICS ", joinColumns = @JoinColumn(name = "FACULTY_ID"), inverseJoinColumns = @JoinColumn(name = "DISP_ACD_YEAR_ID"))
	private List<DesciplineAcademic> academics;

	public LoginAccount getLoginAccount() {
		return loginAccount;
	}

	public void setLoginAccount(LoginAccount loginAccount) {
		this.loginAccount = loginAccount;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getEmploymentType() {
		return employmentType;
	}

	public void setEmploymentType(String employmentType) {
		this.employmentType = employmentType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAddrLine1() {
		return addrLine1;
	}

	public void setAddrLine1(String addrLine1) {
		this.addrLine1 = addrLine1;
	}

	public String getAddrLine2() {
		return addrLine2;
	}

	public void setAddrLine2(String addrLine2) {
		this.addrLine2 = addrLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<DesciplineAcademic> getAcademics() {
		return academics;
	}

	public void setAcademics(List<DesciplineAcademic> academics) {
		this.academics = academics;
	}

	public String getIsSupervisor() {
		return isSupervisor;
	}

	public void setIsSupervisor(String isSupervisor) {
		this.isSupervisor = isSupervisor;
	}

	public Integer getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Integer supervisor) {
		this.supervisor = supervisor;
	}



}
