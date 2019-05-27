import javax.swing.*;
public class QuestionMaker {
   private String question ;
   private String[] answers ;
   private int[] questionAnswers ;
   public QuestionMaker(){}
   public QuestionMaker(String question , String[] answers , int size){
      this.question = question ;
      this.answers = answers ;
      if(size==0){questionAnswers = null;} 
      else{
         questionAnswers = new int[size];}
   }

   public int[] makeOpen(){
      question = JOptionPane.showInputDialog("Shkruaj pyetjen tende");
      answers = new String[1];
      questionAnswers = null ;
      return questionAnswers;
   }
  
  
   public int[] makeYN (){
      question = JOptionPane.showInputDialog("Shkruaj pyetjen tende");
      answers = new String[2];
      answers[0] = "PO";
      answers[1] = "JO";
      questionAnswers = new int[2] ;
      return questionAnswers ;
   } 
  
   public int[] makeScale(){
      int scaleS = Integer.parseInt(JOptionPane.showInputDialog("Nga cili numer fillon vleresimi"));
      int scaleE = Integer.parseInt(JOptionPane.showInputDialog("Te cili numer mbaron vleresimi"));
      question = JOptionPane.showInputDialog("Shkruaj pyetjen tende");
      answers = new String [scaleE - scaleS + 1] ;
      for(int i= 0 ; i<answers.length ; i++){
         answers[i] = JOptionPane.showInputDialog("Shkruaj pergjigjen per vleresimin " + (i+1));
      }
      questionAnswers = new int[scaleE - scaleS + 1]; 
      return questionAnswers ;
   }
   
   public int[] makeMultiple(){
      question = JOptionPane.showInputDialog("Shkruaj pyetjen tende");
      int answNr = Integer.parseInt(JOptionPane.showInputDialog("Sa pergjigje do ti kete pyetja juaj?"));
      answers = new String[answNr] ;
      for(int i= 0 ; i<answers.length ; i++){
         answers[i] = JOptionPane.showInputDialog("Shkruaj pergjigjen e " + (i+1));
      }
      questionAnswers = new int[answNr] ;
      return questionAnswers;
   }
  
   public String getQuestion() {
      return question;
   }
  
   public String[] getAnswers(){
      return answers;
   }  
  
   public int[] getQuestionAnswers(){
      return questionAnswers;
   }
  
   public void vote(int n){
      questionAnswers[n] += 1;
   }
   public void voteS(String s){
      answers[0] = s;
   }
}