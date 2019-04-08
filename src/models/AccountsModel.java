package models;

import entities.Account;

public class AccountsModel extends BaseModel<Account> {
    public AccountsModel() {
        setClazz(Account.class);
    }
}
