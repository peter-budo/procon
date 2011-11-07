package procon.core.plugin.commands;

public class ExecutionRequirements {
    /*[Serializable]
    public class ExecutionRequirements {

        public ExecutionScope ExecutionScope {
            get;
            private set;
        }

        public Privileges RequiredPrivileges {
            get;
            private set;
        }

        public string FailedRequirementsMessage {
            get;
            private set;
        }

        public int MinimumMatchSimilarity {
            get;
            private set;
        }

        public MatchCommand ConfirmationCommand {
            get;
            private set;
        }

        public ExecutionRequirements(ExecutionScope scope) {
            this.Initialize(scope, Privileges.CanLogin, int.MaxValue, null, string.Empty);
        }

        public ExecutionRequirements(ExecutionScope scope, string strFailedRequirementsMessage) {
            this.Initialize(scope, Privileges.CanLogin, int.MaxValue, null, strFailedRequirementsMessage);
        }

        public ExecutionRequirements(ExecutionScope scope, Privileges privileges, string strFailedRequirementsMessage) {
            this.Initialize(scope, privileges, int.MaxValue, null, strFailedRequirementsMessage);
        }

        public ExecutionRequirements(ExecutionScope scope, int iMinimumSimilarity, MatchCommand mcConfirmationCommand) {
            this.Initialize(scope, Privileges.CanLogin, iMinimumSimilarity, mcConfirmationCommand, string.Empty);
        }

        public ExecutionRequirements(ExecutionScope scope, int iMinimumSimilarity, MatchCommand mcConfirmationCommand, string strFailedRequirementsMessage) {
            this.Initialize(scope, Privileges.CanLogin, iMinimumSimilarity, mcConfirmationCommand, strFailedRequirementsMessage);
        }

        public ExecutionRequirements(ExecutionScope scope, Privileges privileges, int iMinimumSimilarity, MatchCommand mcConfirmationCommand, string strFailedRequirementsMessage) {
            this.Initialize(scope, privileges, iMinimumSimilarity, mcConfirmationCommand, strFailedRequirementsMessage);
        }

        private void Initialize(ExecutionScope scope, Privileges privileges, int iMinimumSimilarity, MatchCommand mcConfirmationCommand, string strFailedRequirementsMessage) {
            this.ExecutionScope = scope;
            this.RequiredPrivileges = privileges;
            this.MinimumMatchSimilarity = iMinimumSimilarity;
            this.ConfirmationCommand = mcConfirmationCommand;

            if (strFailedRequirementsMessage.Length < 100) {
                this.FailedRequirementsMessage = strFailedRequirementsMessage;
            }
            else {
                this.FailedRequirementsMessage = strFailedRequirementsMessage.Substring(0, 100);
            }
        }

        public bool HasValidPermissions(CPrivileges privileges) {

            bool canExecuteCommand = false;

            if (this.ExecutionScope == ExecutionScope.All) {
                canExecuteCommand = true;
            }
            else if (this.ExecutionScope == ExecutionScope.Account) {
                if (privileges != null) {
                    canExecuteCommand = true;
                }
            }
            else if (this.ExecutionScope == ExecutionScope.Privileges) {
                if (privileges != null && privileges.Has(this.RequiredPrivileges) == true) {
                    canExecuteCommand = true;
                }
            }

            return canExecuteCommand;
        }
    }*/
}
