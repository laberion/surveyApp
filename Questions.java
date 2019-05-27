import javax.swing.*;
public class Questions {
   public QuestionMaker[] makeQuestions ( QuestionMaker[] questions , int numberOfQuestions){
      for(int i=0 ; i<numberOfQuestions ; i++){
         questions[i] = new QuestionMaker();
      }
      
      for(int i=0 ; i<questions.length ; i++){
         try {int a = Integer.parseInt(JOptionPane.showInputDialog("Lloji i pyetjse se " + (i+1) + "\n" + " Shtyp " + "\n" + "1 - per pyetje PO/JO " + 
               "\n" + "2 - Per pyetje me shkalle vleresimi" + "\n" + "3 - Per pyetje te hapur"
               + "\n" + "4 - Per pyetje me shume pergjigje"));
            switch(a) {
               case 1 : 
                  { questions[i].makeYN() ; } 
                  break;
               case 2 : 
                  { questions[i].makeScale(); } 
                  break;
               case 3 : 
                  { questions[i].makeOpen(); } 
                  break;
               case 4 : 
                  { questions[i].makeMultiple(); } 
                  break;
               default : 
                  { JOptionPane.showMessageDialog(null, " Per momentin nuk mund te krijojme kete lloj pyetje ");}
            } } 
         catch(Exception e){JOptionPane.showMessageDialog(null, "Ju lutem jepini te dhenat e sakta"); i--;}
      }
      
      JOptionPane.showMessageDialog(null, "Keni perfunduar krijimin e pyetjeve per anketen tuaj");
      return questions ;
   }
}