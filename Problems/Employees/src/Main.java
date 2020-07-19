class Employee {
    String name;
    String email;
    int experience;

    Employee(String name, String email, int experience) {
        this.name = name;
        this.email = email;
        this.experience = experience;
    }

    String getName() {
        return name;
    }

    String getEmail() {
        return email;
    }

    int getExperience() {
        return experience;
    }
}

class Developer extends Employee {
    String mainLanguage;
    String[] skills;

    Developer(String name, String email, int experience, String mainLanguage, String[] skills) {
        super(name, email, experience);
        this.mainLanguage = mainLanguage;
        this.skills = skills;
    }

    public String getMainLanguage() {
        return mainLanguage;
    }

    public String[] getSkills() {
        return skills;
    }
}

class DataAnalyst extends Employee {
    Boolean phd;
    String[] methods;

    DataAnalyst(String name, String email, int experience, Boolean phd, String[] methods) {
        super(name, email, experience);
        this.phd = phd;
        this.methods = methods;
    }

    Boolean isPhd() {
        return phd;
    }

    public String[] getMethods() {
        return methods;
    }
}