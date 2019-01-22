package lottery;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

class CProcessConfig {
	public String m_PrizeLevel;
	public int m_PrizeNum;
	public int m_NumPerRound;
	public String m_PrizeType;
	public String m_SpecialNames[] = {""};
	private boolean m_debug = true; 
	
	public CProcessConfig(String ConfigLine) {
		m_PrizeLevel 	= "";
		m_PrizeNum 		= 0;
		m_NumPerRound 	= 0;
		m_PrizeType		= "";
		if ( ConfigLine != "" )
		{
			String[] words = ConfigLine.split(",");
			int NumWords = words.length; 
			
			if ( m_debug ) { System.out.println( "NumWords: "+NumWords); }
			
			if ( NumWords > 0 )
			{
				m_PrizeLevel = words[0].trim();
				if ( m_debug ) { System.out.println( "m_PrizeLevel: "+m_PrizeLevel); }
			}
			if ( NumWords > 1 )
			{
				m_PrizeNum = Integer.parseInt(words[1]);
				if ( m_debug ) { System.out.println( "m_PrizeNum: "+m_PrizeNum); }
			}
			if ( NumWords > 2 )
			{
				m_NumPerRound = Integer.parseInt(words[2]);
				if ( m_debug ) { System.out.println( "m_NumPerRound: "+m_NumPerRound); }
			}
			if ( NumWords > 3 )
			{
				m_PrizeType = words[3].trim();
				if ( m_debug ) { System.out.println( "m_PrizeType: "+m_PrizeType); }
			}
			if ( NumWords > 4 )
			{
				int idx = 0;
				while(NumWords - idx - 4 > 0)
				{
					m_SpecialNames[idx] = words[4+idx].trim();
					if ( m_debug ) { System.out.println( "m_SpecialNames["+idx+"]: "+m_SpecialNames[idx]); }
					idx ++;
				}
			}			
		}
	}
}

public class CInputs {
	public CProcessConfig m_Process[] = new CProcessConfig[20];
	public int m_ProcessCurrentLine = 0;
	public int m_ProcessCurrentRound = 0;
	private List<String> m_NameList = new ArrayList<String>();
	private int m_NameListNum = 0;
	private boolean m_debug = true; 	
	
	public CInputs(){
		LoadProcessConfig();
		LoadNamelist();
	}

	public String[] StartStopRound(String operation) {
		String[] str = new String[10];
		
		if ( m_debug ) { System.out.println( "operation: "+operation+"m_Process.length: "+m_Process.length); }
		
		for ( int k = 0; k < str.length; k++)
		{
			str[k] = "";
		}
		// check if all lines are proceeded
		//if ( m_ProcessCurrentLine >= m_Process.length-1 )
		if ( m_Process[m_ProcessCurrentLine] == null )
		{
			str[0] = "所有奖项都已抽完！";	
			return str;
		}
		/*
		 * Get the current process config
		 * */
		int divider = m_Process[m_ProcessCurrentLine].m_NumPerRound;
		if ( divider <= 0 )
		{
			divider = m_Process[m_ProcessCurrentLine].m_PrizeNum;
		}
		// how many rounds needed for the current line
		int rounds =  m_Process[m_ProcessCurrentLine].m_PrizeNum / divider;
		if ( m_Process[m_ProcessCurrentLine].m_PrizeNum % divider > 0)
		{
			rounds ++;
		}
		if ( m_ProcessCurrentRound == 0 )
		{
			// initialize the current round
			m_ProcessCurrentRound = rounds;
		}
		str[0] = m_Process[m_ProcessCurrentLine].m_PrizeLevel;		
		str[1] = m_Process[m_ProcessCurrentLine].m_PrizeType;
		
		if ( m_debug ) { System.out.println( "++m_ProcessCurrentRound: "+m_ProcessCurrentRound); }
		if ( m_debug ) { System.out.println( "++m_ProcessCurrentLine: "+m_ProcessCurrentLine); }
		
		if ( m_ProcessCurrentRound > 0 )
		{
			if ( operation == "stop" )
			{
				m_ProcessCurrentRound -- ;
			}
			
			// Randomize the namelist
			Collections.shuffle(m_NameList);
			// TRICKY: special deal
			
			for (int i = 0; i < divider; i++)
			{
				str[2+i] = m_NameList.get(i);
				
				if ( m_debug ) { System.out.println( "random: str[2+"+i+"]: "+str[2+i]); }
				
				if ( operation == "stop" )
				{
					m_NameList.remove(i);
				}
			}
		}
		
		 // all rounds finished for this line, switch to another line
		if ( m_ProcessCurrentRound == 0 )
		{
			m_ProcessCurrentLine ++;
		}

		if ( m_debug ) { System.out.println( "--m_ProcessCurrentRound: "+m_ProcessCurrentRound); }
		if ( m_debug ) { System.out.println( "--m_ProcessCurrentLine: "+m_ProcessCurrentLine); }

		return str;
	}
	
	private void LoadNamelist() {
		InputStream is = CLottery.class.getClassLoader().getResourceAsStream("namelist.txt");
		try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
		    String line;
		    if ( m_debug ) { System.out.println("initial name list: ");}
		    while ((line = br.readLine()) != null) {
		        if ( m_debug ) { System.out.println(line);}
		        m_NameList.add(line);
		    }
		    m_NameListNum = m_NameList.size();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	private void LoadProcessConfig() {
		InputStream is = CLottery.class.getClassLoader().getResourceAsStream("process.txt");
		try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
		    String line;
		    int lineNum = -1;
		    while ((line = br.readLine()) != null) {
		    	if ( m_debug ) { System.out.println( "LoadProcessConfig, line: "+line+" of lineNum: "+lineNum); }
		        if ( lineNum >= 0 ) // Skip first line which is a comment
		        {
		        	m_Process[lineNum] = new CProcessConfig(line);
		        }
		        lineNum ++;
		    }
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	private int random(int max) {
		return (int) (Math.random() * max);
	}	
}
