package QuizAppWithTimer;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Quiz {

	private static final int TotalQuestions = 3;
	private static final int TimeLimitPerQuestion = 10; //In Seconds
	private static int Score = 0;
	private static int CurrentQuestionIndex = 0;
	private static Scanner s=new Scanner(System.in);
	private static Timer Timer;
	
	private static Question[] Questions = {
			new Question("Chhatrapati Shivaji Maharaj crowned himself at?", new String[]{"A. Poona","B. Kokan","C. Biapur","D.Raigad"}, 3),
			new Question("Under Shivaji Maharaj's rule, the head of a unit of 25 in the Cavalry was known as?", new String[]{"A. Jumadar","B. Havaldar","C. Faujdar","D. Hazari"}, 1),
			new Question("Shivaji Maharaj founded the Maratha kingdom by annexing the territories of", new String[]{"A. Bijapur","B. Mughals","C. Both (a) and (b)","D. Bijapur, Golcunda and the Mughals"}, 2)
	};
	
	public static void main(String args[])
	{
		StartQuiz();
	}
	
	private static void StartQuiz()
	{
		System.out.println("Welcome to the Quiz!");
	
		Timer = new Timer();
		DisplayQuestion();
		
		Timer.schedule(new TimerTask() {
			@Override
			public void run()
			{
				System.out.println("Time is up!");
				DisplayQuestion();
			}
		}, TimeLimitPerQuestion*1000);
	}
	
	private static void DisplayQuestion()
	{
		if(CurrentQuestionIndex < TotalQuestions)
		{
			Question CurrentQuestion = Questions[CurrentQuestionIndex];
			System.out.println(CurrentQuestion.getQuestion());
			
			for(String option:CurrentQuestion.getOptions())
			{
				System.out.println(option);
			}
			
			System.out.println("Enter Choice: ");
			String UserChoice=s.nextLine();
			CheckAnswer(CurrentQuestion, UserChoice);
		}
		else
		{
			EndQuiz();
		}
	}
	
	private static void CheckAnswer(Question Question, String UserChoice)
	{
		int UserChoiceIndex = -1;
		switch(UserChoice.toLowerCase())
		{
			case "a":
				UserChoiceIndex=0;
				break;
			case "b":
				UserChoiceIndex=1;
				break;
			case "c":
				UserChoiceIndex=2;
				break;
			case "d":
				UserChoiceIndex=3;
				break;
			default:
				System.out.println("Invalid Choice!");
				break;
		}
		
		if(UserChoiceIndex == Question.getCorrectAnswerIndex())
		{
			Score++;
			System.out.println("Correct!");
		}
		else
		{
			System.out.println("Incorrect! The correct answer is: "+Question.getOptions()[Question.getCorrectAnswerIndex()]);
		}
		
		CurrentQuestionIndex++;
		DisplayQuestion();
	}
	
	private static void EndQuiz()
	{
		System.out.println("Quiz Finished!");
		System.out.println("Your Score: "+Score+"/"+TotalQuestions);
		s.close();
		Timer.cancel();
	}
	
	static class Question
	{
		private String Question;
		private String[] Options;
		private int CorrectAnswerIndex;
		
		public Question(String Question, String[] Options, int CorrectAnswerIndedx)
		{
			this.Question=Question;
			this.Options=Options;
			this.CorrectAnswerIndex=CorrectAnswerIndex;
		}
		
		public String getQuestion()
		{
			return Question;
		}
		
		public String[] getOptions() 
		{
			return Options;
		}
		
		public int getCorrectAnswerIndex()
		{
			return CorrectAnswerIndex;
		}
	}

}
