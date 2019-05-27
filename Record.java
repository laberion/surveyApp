import javax.swing.*;
public class Record {
   private QuestionMaker[] questions ;
   private Key id ;
 
   public Record (QuestionMaker[] questions , Key id){
      this.questions = questions ;
      this.id = id;
   }
 
   public QuestionMaker[] getQuestions () {
      return questions;}
   public Key getKey() { 
      return id;}
 
   public QuestionMaker[] getAnswers (){
      for(int j = 0 ; j< questions.length; j++){
         String answers = "";
         for(int k=0 ; k < questions[j].getAnswers().length ; k++){
            answers += "\n" + k + " - " + questions[j].getAnswers()[k];
         }
         if(questions[j].getQuestionAnswers() == null){
            String vote = JOptionPane.showInputDialog(questions[j].getQuestion());
            questions[j].voteS(vote);
         }
         else{
            try{    int vote = Integer.parseInt(JOptionPane.showInputDialog(questions[j].getQuestion() + answers ));
               if(vote >= 0 && vote < questions[j].getQuestionAnswers().length){
                  questions[j].vote(vote);
               }
               else { JOptionPane.showMessageDialog(null,"Ju lutem shkruani numrin e sakte te pergjigjes");
                  j -= 1;
               }} 
            catch (Exception e){JOptionPane.showMessageDialog(null,"Ju lutem jepni te dhenat e sakta"); j--;}
         }
      }
      return questions;
   }
   
   public String getVoterAnsw (){
      String answ = "\n";
      for(int i =0; i<questions.length ;i++){
         if(questions[i].getQuestionAnswers() == null) {answ += questions[i].getAnswers()[0]+ "  \n"; }
         else { 
            int index = 0 ;
            boolean p = true;
            while(p){
               if( questions[i].getQuestionAnswers()[index] == 1 ) {answ += questions[i].getAnswers()[index] + "  \n" ; p=false;}
               else { index ++;}
            }
         }         
      }
       
      return answ ;
   }
   
   public String getVoterResp (){
      String rez = "\n";
      for(int i =0;i<questions.length ;i++){
         if(questions[i].getQuestionAnswers() == null){rez+= questions[i].getAnswers()[0];}
         else{
            for(int j=0 ; j<questions[i].getQuestionAnswers().length ; j++){
               if(questions[i].getQuestionAnswers()[j]==1){rez+= questions[i].getAnswers()[j] + "   \n";}
            }
         }
      }
      return rez;
   }
}