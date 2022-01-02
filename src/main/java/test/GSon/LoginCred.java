package test.GSon;

public class LoginCred {
    private String username;
    private String password;
    private Job job;

    public LoginCred(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Job getJob() {
        return job;
    }

    public static class Job{
        private String position;

        @Override
        public String toString() {
            return "Job{" +
                    "position='" + position + '\'' +
                    '}';
        }

        public String getPosition(){
            return position;
        }

    }

    @Override
    public String toString() {
        return "LoginCred{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", job=" + job +
                '}';
    }
}
