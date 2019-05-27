import javax.swing.*;
public class SurveyApp {
   public static void main(String[] args){
      try { String title = JOptionPane.showInputDialog("Titulli i anketes");
         int numberOfQuestions = Integer.parseInt(JOptionPane.showInputDialog("Sa pyetje do ti kete anketa juaj?"));
         QuestionMaker[] questions = new QuestionMaker[numberOfQuestions]; 
         questions = new Questions().makeQuestions(questions,numberOfQuestions);
      
         int surveyNR = Integer.parseInt(JOptionPane.showInputDialog("Sa kandidat do ti nenshtrohen anketes"));  
            
         Database base = new Database(surveyNR);
         for ( int i = 0 ; i < surveyNR ; i++){
            base.insert(new Record(questionMaker(questions,numberOfQuestions),new Key(i)));
         }
      
         for(int i = 0;i<surveyNR ; i++){
            JOptionPane.showMessageDialog(null,"Kandidati " + i + " te pergaditet per votim");
            base.find(new Key(i)).getAnswers();
            JOptionPane.showMessageDialog(null,"Kandidati " + i + " ka perfunduar votimin");
         }
         JOptionPane.showMessageDialog(null,"Jane mbledhur pergjigjet nga "+ surveyNR + " kandidate");
         whatNext(title,base ,surveyNR,questions); } 
      catch(Exception e){JOptionPane.showMessageDialog(null,"Nuk mund te krijohet ankete me parametrat e dhene");}
   }
   
   private static void whatNext(String title,Database base , int surveyNR,QuestionMaker[] questions){
      try{int a = Integer.parseInt(JOptionPane.showInputDialog("Shtyp 1 per te shfaqur statistikat e pergjithshme te anketes \n Shtyp 2 per te marre pergjigjet e ndonje votuesi \n Shtyp 3 per te mbyllur programin"));
         switch(a) {
            case 1 : 
               {
                  String output = "";
                  for(int i = 0 ; i<questions.length ; i++){
                     if(questions[i].getQuestionAnswers() == null){
                        output += "Pergjigjet ne pyetjen " + questions[i].getQuestion() + "  \n";
                        for(int j=0 ; j< surveyNR ; j++){
                           output +=  base.find(new Key(j)).getQuestions()[i].getAnswers()[0] + "  \n";
                        }
                        MyWriter obj = new MyWriter(title,400,400);
                        obj.writeSentence(output);
                     }
                     else{
                        output += "Ne pyetjen " + questions[i].getQuestion() + "  \n";
                     
                        for(int m=0; m<questions[i].getQuestionAnswers().length ; m++){
                           int tempA =0;
                           for(int k=0 ; k<surveyNR ; k++){
                              tempA += base.find(new Key(k)).getQuestions()[i].getQuestionAnswers()[i] ;
                           }
                           output += " ne pergjigjen " + questions[i].getAnswers()[m] + " kane votuar " + tempA + " votues   \n";
                        }
                        MyWriter obj = new MyWriter(title,400,400);
                        obj.writeSentence(output);
                     }
                  }
               } 
               break;
            case 2 : 
               {int b = Integer.parseInt(JOptionPane.showInputDialog("Pergjigjet e cilit kandidat i deshironi (Nga gjithsej 0-" + (surveyNR-1) + " kandidate"));
                  if(b>=0 && b<surveyNR){
                     String rez = "Pergjigjet e kandidatit " + b +" - \n " ;
                     rez += base.find(new Key(b)).getVoterResp() ;
                     MyWriter obj = new MyWriter(title,400,400);
                     obj.writeSentence(rez);
                     whatNext(title,base,surveyNR,questions);
                  }
                  else{JOptionPane.showMessageDialog(null,"Kandidate me qelesin e dhene nuk ekziston"); whatNext(title,base ,surveyNR,questions);}             
               } 
               break;
            case 3 : 
               {JOptionPane.showMessageDialog(null,"Keni perfunduar anketen tuaj") ; } 
               break;
            default : 
               { JOptionPane.showMessageDialog(null,"Ky opsion nuk ekziston") ; whatNext(title,base ,surveyNR,questions);}
         }} 
      catch(Exception e){JOptionPane.showMessageDialog(null,"Ju lutem jepni komanden e sakte"); whatNext(title,base ,surveyNR,questions);}
   }
   
   private static QuestionMaker[] questionMaker(QuestionMaker[] questions,int numberOfQuestions){
      QuestionMaker[] tempBase = new QuestionMaker[numberOfQuestions];
      for(int j=0;j<numberOfQuestions ; j++){
         String tempQuestion = questions[j].getQuestion();
         String[] tempAnswers ;
         if(questions[j].getQuestionAnswers() == null){tempAnswers = new String[1];}
         else{tempAnswers = questions[j].getAnswers();}
         int size ;
         if(questions[j].getQuestionAnswers() == null){size=0; } 
         else{size=questions[j].getQuestionAnswers().length;}
         tempBase[j] = new QuestionMaker(tempQuestion,tempAnswers,size);
      }
      return tempBase ;
   }
}