package User;

public class User {
    private String nickname;
    private String password;
    private String realName;
    private String surname;
    private int age;
    private String email;
    private String selectedLogoPath;


	public User(String nickname, String password, String realName, String surname, int age, String email,String selectedLogoPath) {
        this.nickname = nickname;
        this.password = password;
        this.realName = realName;
        this.surname = surname;
        this.age = age;
        this.email = email;
        this.selectedLogoPath = selectedLogoPath;
    }
	
	public String getSelectedLogoPath() {
		return selectedLogoPath;
	}

	public void setSelectedLogoPath(String selectedLogoPath) {
		this.selectedLogoPath = selectedLogoPath;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


    @Override
    public String toString() {
        return nickname + "," + password + "," + realName + "," + surname + "," + age + "," + email + "," + selectedLogoPath;
    }

}
