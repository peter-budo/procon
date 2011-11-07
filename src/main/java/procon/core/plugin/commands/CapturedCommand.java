package procon.core.plugin.commands;

public class CapturedCommand {
    /*[Serializable]
    public class CapturedCommand : IComparable<CapturedCommand> {

        /// <summary>
        /// This is the first character #/!/@ or whatever passed.
        /// </summary>
        public string ResposeScope {
            get;
            private set;
        }

        /// <summary>
        /// The command it was matched against.
        /// </summary>
        public string Command {
            get;
            private set;
        }

        /// <summary>
        /// A list of matched arguments which will also have the matched score to the dictionaries.
        /// If all of the matched scores == 0, then it is an entire match.
        /// </summary>
        public List<MatchArgument> MatchedArguments {
            get;
            private set;
        }

        /// <summary>
        /// Anything after the scope, command and arguments.
        /// !fmove [playername] [squad]
        /// !fmove [Phogue] [Delta] [This is an example of extra arguments]
        /// </summary>
        public string ExtraArguments {
            get;
            private set;
        }

        /// <summary>
        /// !kick pho
        /// Did you mean !kick Phogue?
        /// !yes
        /// IsConfirmed == true
        ///
        /// !kick phogue
        /// IsConfirmed == true
        /// </summary>
        public bool IsConfirmed {
            get;
            set;
        }

        *//*
        public int AggregateMatchScore {
            get {
                int returnScore = 0;

                foreach (MatchArgument arg in this.MatchedArguments) {
                    returnScore += arg.MatchScore;
                }

                return returnScore;
            }
        }

        public float PercentageMatchScore {
            get {
                float returnPercentage = 0.0F;

                foreach (MatchArgument arg in this.MatchedArguments) {
                    if (arg.Argument.Length > 0) {
                        returnPercentage += (float)(arg.Argument.Length - arg.MatchScore) / (float)arg.Argument.Length;
                    }
                }

                if (this.MatchedArguments.Count > 0) {
                    returnPercentage /= (float)this.MatchedArguments.Count;
                }

                return returnPercentage;
            }
        }
        *//*
        public CapturedCommand(string strResposeScope, string strCommand, List<MatchArgument> lstMatchedArguments, string strExtraArguments) {
            this.ResposeScope = strResposeScope;
            this.Command = strCommand;
            this.MatchedArguments = lstMatchedArguments;
            this.ExtraArguments = strExtraArguments;

            this.IsConfirmed = false;
        }

        public override string ToString() {

            string strString = String.Format("{0}{1}", this.ResposeScope, this.Command);

            foreach (MatchArgument mtcArgument in this.MatchedArguments) {
                strString = String.Format("{0} {1}", strString, mtcArgument.Argument);
            }

            return strString;
        }

        #region IComparable<CapturedCommand> Members

        public int CompareTo(CapturedCommand other) {

            float thisPercentage = 0.0F, otherPercentage = 0.0F;
            double highestCount = Math.Max(this.MatchedArguments.Count, other.MatchedArguments.Count);

            foreach (MatchArgument arg in this.MatchedArguments) {
                if (arg.Argument.Length > 0) {
                    thisPercentage += (float)(arg.Argument.Length - arg.MatchScore) / (float)arg.Argument.Length;
                }
            }

            foreach (MatchArgument arg in other.MatchedArguments) {
                if (arg.Argument.Length > 0) {
                    otherPercentage += (float)(arg.Argument.Length - arg.MatchScore) / (float)arg.Argument.Length;
                }
            }

            if (this.MatchedArguments.Count > 0) {
                thisPercentage /= (float)highestCount;
                otherPercentage /= (float)highestCount;
            }

            return thisPercentage.CompareTo(otherPercentage);
        }

        #endregion
    }*/
}
