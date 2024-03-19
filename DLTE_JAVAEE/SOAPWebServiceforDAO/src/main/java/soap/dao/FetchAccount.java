package soap.dao;
import org.example.Entity.UserDetails;

import java.util.List;

public class FetchAccount {
        private List<UserDetails> userDetailsList;

        public List<UserDetails> getuserDetailsList() {
            return userDetailsList;
        }

        public void setUserDetailsList(List<UserDetails> userDetailsList) {
            this.userDetailsList = userDetailsList;
        }

    @Override
    public String toString() {
        return "FetchAccount{" +
                "userDetailsList=" + userDetailsList +
                '}';
    }
}
