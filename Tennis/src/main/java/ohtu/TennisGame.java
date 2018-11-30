package ohtu;

public class TennisGame {
    
    private int score_player1 = 0;
    private int score_player2 = 0;
    private String player1;
    private String player2;

    public TennisGame(String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void addPointToScore(String playerName) {
        if (playerName.equals("player1")) {
             score_player1 += 1;
        } else {
            score_player2 += 1;
        }
    }

    public String getTempScore() {
        String score="";
        int tempScore=0;
        for (int i=1; i<3; i++) {               
                if (i==1) {
                    tempScore = score_player1;
                } else { 
                    score+="-"; 
                    tempScore = score_player2;
                }
            switch(tempScore) {
                    case 0:
                        score+="Love";
                        break;
                    case 1:
                        score+="Fifteen";
                        break;
                    case 2:
                        score+="Thirty";
                        break;
                    case 3:
                        score+="Forty";
                        break;
                }              
            }
        return score;
    }
    
    public String getScoreForPlayer1(int score_player1) {
        String score="";
        switch (score_player1) {
                case 0:
                        score = "Love-All";
                    break;
                case 1:
                        score = "Fifteen-All";
                    break;
                case 2:
                        score = "Thirty-All";
                    break;
                case 3:
                        score = "Forty-All";
                    break;
                default:
                        score = "Deuce";
                    break;                
            }
        return score;
    }
    
    public String getDifference(int difference) {
        String score="";
        if (difference==1) {
                score ="Advantage player1";
            } else if (difference ==-1) {
                score ="Advantage player2";
            } else if (difference>=2) {
                score = "Win for player1";
            } else {
                score ="Win for player2";
            }
        return score;
    }
    
    public String getScore() {
        String score = "";        
        if (score_player1==score_player2) {
            score=getScoreForPlayer1(score_player1);           
        } else if (score_player1>=4 || score_player2>=4) {
            int difference = score_player1-score_player2;
            score=getDifference(difference);
        } else {
            score=getTempScore();
        }
        return score;
    }
}