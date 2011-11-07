package procon.core.accounts;

public class AccountDictionary {
    /*public class AccountDictionary : KeyedCollection<string, Account> {

        public delegate void AccountAlteredHandler(Account item);
        public event AccountAlteredHandler AccountAdded;
        public event AccountAlteredHandler AccountChanged;
        public event AccountAlteredHandler AccountRemoved;

        protected override string GetKeyForItem(Account item) {
            return item.Name;
        }

        protected override void InsertItem(int index, Account item) {
            base.InsertItem(index, item);

            if (this.AccountAdded != null) {
                FrostbiteConnection.RaiseEvent(this.AccountAdded.GetInvocationList(), item);
            }
        }

        protected override void RemoveItem(int index) {

            if (this.AccountRemoved != null) {
                FrostbiteConnection.RaiseEvent(this.AccountRemoved.GetInvocationList(), this[index]);
            }

            base.RemoveItem(index);
        }

        protected override void SetItem(int index, Account item) {
            if (this.AccountChanged != null) {
                FrostbiteConnection.RaiseEvent(this.AccountChanged.GetInvocationList(), item);
            }

            base.SetItem(index, item);
        }

        public void CreateAccount(string strUsername, string strPassword) {
            if (this.Contains(strUsername) == true) {
                this[strUsername].Password = strPassword;
            }
            else {
                Account accNewAccount = new Account(strUsername, strPassword);

                // Temporary until privileges can be extracted from here and into the layer.
                *//*
                foreach (Account accCurrentAccount in this) {
                    foreach (AccountPrivilege accCurrentPrivilege in accCurrentAccount.AccountPrivileges) {
                        if (accNewAccount.AccountPrivileges.Contains(accCurrentPrivilege.HostNamePort) == false) {
                            accNewAccount.AccountPrivileges.Add(new AccountPrivilege(accNewAccount, accCurrentPrivilege.HostNamePort, new CPrivileges()));
                        }
                    }
                }
                *//*
                this.Add(accNewAccount);
            }
        }

        public void DeleteAccount(string strUsername) {
            if (this.Contains(strUsername) == true) {
                this.Remove(strUsername);
            }
        }

        public void ChangePassword(string strUsername, string strPassword) {
            if (this.Contains(strUsername) == true) {
                this[strUsername].Password = strPassword;
            }
        }

        public List<string> ListAccountNames() {
            return new List<string>(this.Dictionary.Keys);
        }

    }*/

}
