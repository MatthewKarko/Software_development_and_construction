Input API: AlphaVantage \
Output API: Pastebin \
Claimed Tier: Distinction\
Credit Optional Feature 1: Theme song\
Credit Optional Feature 2: About\
Distinction Optional Feature: Spinning progress indicator\
High Distinction Optional Feature: <br />

Milestone 1 Submission: <br />
  SHA: 6a15be010a2e34607fbae0e8700418c001b44930 <br />
  URI: https://github.sydney.edu.au/mkar6206/SCD2_2022/commit/6a15be010a2e34607fbae0e8700418c001b44930#diff-d010c0645d9236cff2a125742604305aaa5ee379991f3b702b729f5717bad94c <br />
Milestone 1 Re-Submission: <br />
  SHA: <br />
  URI: <br />
Milestone 2 Submission: <br />
  SHA: 987dd4bcf01294cd1c666eed342a020eaed4becc\
  URI: https://github.sydney.edu.au/mkar6206/SCD2_2022/commit/987dd4bcf01294cd1c666eed342a020eaed4becc#diff-d010c0645d9236cff2a125742604305aaa5ee379991f3b702b729f5717bad94c <br />
Milestone 2 Re-Submission: <br />
  SHA: 008858d0247add531a952a04d80afcbd784ba4e7\
  URI: https://github.sydney.edu.au/mkar6206/SCD2_2022/commit/008858d0247add531a952a04d80afcbd784ba4e7#diff-d010c0645d9236cff2a125742604305aaa5ee379991f3b702b729f5717bad94c <br />
Exam Base Commit: <br />
  SHA: 008858d0247add531a952a04d80afcbd784ba4e7<br />
  URI: https://github.sydney.edu.au/mkar6206/SCD2_2022/commit/008858d0247add531a952a04d80afcbd784ba4e7#diff-d010c0645d9236cff2a125742604305aaa5ee379991f3b702b729f5717bad94c<br />
Exam Submission Commit: <br />
  SHA: 0685d8ffe740ad97c75af3258750fc93cb8103d4 <br />
  URI: https://github.sydney.edu.au/mkar6206/SCD2_2022/commit/0685d8ffe740ad97c75af3258750fc93cb8103d4#diff-d010c0645d9236cff2a125742604305aaa5ee379991f3b702b729f5717bad94c<br />


# Instructions #
This application has two versions of each API, online and offline. For this application you should ensure that you have the correct gradle and java versions installed and that
you have the correct API keys set in your environment variables. 

## Running offline ##
You can either do this through an IDE such as Intellij, or through a command prompt terminal or windows terminal. Simply run the line `gradle run --args="offline offline"` for 
the complete offline mode.

## Running online ##
You can either do this through an IDE such as Intellij, or through a command prompt terminal or windows terminal. Simply run the line `gradle run --args="online online"` for 
the complete online mode.

## Running mixed modes ##
You can either do this through an IDE such as Intellij, or through a command prompt terminal or windows terminal. Simply run the line `gradle run --args="offline online"` or
`gradle run --args="online offline"`.

# Application Quirks #
With the API , there are some cases where the tickers that it shows for best matches dont actually have any information, however i have accounted for this in the code. The search function is case insensitive so you can either search "IBM" or "ibm" and you will receive the same results. You MUST search for a stock prior to wanting to view the information on the stock. 

# Citations #
AutoCompleteComboBoxListener.java - https://gist.github.com/yelken/46063302acdc230f446a <br />
Background Music - https://www.youtube.com/watch?v=UttTfT4pdHo&ab_channel=BetterTracks <br />
Loading Gif - https://tenor.com/view/load-loading-waiting-gif-5662595 <br />
App logo - https://www.pngfind.com/mpng/hTRhhTx_aa-logo-png-letter-a-logo-png-transparent/ <br />
