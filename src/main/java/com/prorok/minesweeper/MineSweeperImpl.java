package com.prorok.minesweeper;

import java.util.stream.Stream;

public class MineSweeperImpl implements MineSweeper {
	
	private String settedMineField;

	public void setMineField(String mineField) throws IllegalArgumentException {
		StringBuilder sb = new StringBuilder();
		String errMessage = "Wrong format of String";
		int counter =0;
		int mCharacters  = 0;
		sb.append(mineField);
		if(sb.length()==0) throw new IllegalArgumentException("String to short!");
		for(int index=0;index<sb.length();index++) {
			if((sb.charAt(index) == '*') || (sb.charAt(index) == '.')) {
					counter ++;
				if(index == sb.length()-1 && mCharacters!=counter) {
					throw new IllegalArgumentException(errMessage);
				}
			}else if(sb.charAt(index) == '\n'){
				if(mCharacters == 0) {
					mCharacters=counter;
				}else {
					if(mCharacters!=counter) throw new IllegalArgumentException(errMessage);
				}
					counter = 0;
			}else throw new IllegalArgumentException(errMessage);
		}
		System.out.println("You crate a game. String is correct");
		settedMineField = sb.toString();
	}

	public String getHintField() throws IllegalStateException {
		String[] rows = settedMineField.split("\n");
		String[][] elements = new String[rows.length][rows[1].length()];
		for(int i=0;i<rows.length;i++) {
			String[] split = rows[i].split("");
			for(int j=0;j<split.length;j++) {				
				elements[i][j] = split[j];
			}
		}				
		
		StringBuilder sb = new StringBuilder();
		for(int x=0;x<elements.length;x++) {
			for(int y=0;y<elements[x].length;y++) {	
			    StringBuilder toCheck = new StringBuilder();
		        int counter = 0;
				if("*".equals(elements[x][y])) {
					sb.append(elements[x][y]);
				}else
				if(x==0 && y==0) {
					    leftUpperCorner(elements, sb, toCheck, counter, x, y);
					    clear(toCheck,counter); 
				}else
				if(x==0 && y==elements[x].length-1) {
					leftBottomCorner(elements, sb, x, toCheck, counter);
						clear(toCheck,counter);   
				}else			
				if(x==elements.length-1 && y==0) {
					rightUpperCorner(elements, sb, y, toCheck, counter);
						clear(toCheck,counter);   		
				}else
				if(x==elements.length-1 && y==elements[x].length-1) {
					rightBottomCorner(elements, sb, x, toCheck, counter);
						clear(toCheck,counter);   		
				}else
				if(x==0) {
					 upperSide(elements, sb, x, y, toCheck, counter);
						clear(toCheck,counter);   
				}else
				if(y==0) {
					 leftSide(elements, sb, x, y, toCheck, counter);
						clear(toCheck,counter);   			
				}else
				if(x==elements.length-1) {
						 bottomSide(elements, sb, y, toCheck, counter);
							clear(toCheck,counter);  
				}else
				if(y==elements[x].length-1) {
					rightSide(elements, sb, x, toCheck, counter);
						clear(toCheck,counter);   
				}
				else {
					around(elements, sb, x, y, toCheck, counter);
						clear(toCheck,counter);				
				}
			}
		}	
		return sb.toString();
	}

  private void around(String[][] elements, StringBuilder sb, int x, int y, StringBuilder toCheck,
      int counter) {
    toCheck.append(elements[x-1][y-1]);
    toCheck.append(elements[x-1][y]);	
    toCheck.append(elements[x-1][y+1]);
    toCheck.append(elements[x][y-1]);
    toCheck.append(elements[x][y+1]);
    toCheck.append(elements[x+1][y-1]);
    toCheck.append(elements[x+1][y]);
    toCheck.append(elements[x+1][y+1]);
    for(int i=0;i<toCheck.length();i++) {
    	if(toCheck.charAt(i) == '*') {counter++;}
    	}
    	sb.append(counter);
  }

  private void rightSide(String[][] elements, StringBuilder sb, int x, StringBuilder toCheck,
      int counter) {
    toCheck.append(elements[x-1][elements[x].length-1]);
    toCheck.append(elements[x+1][elements[x].length-1]);
    toCheck.append(elements[x-1][elements[x].length-2]);
    toCheck.append(elements[x][elements[x].length-2]);
    toCheck.append(elements[x+1][elements[x].length-2]);
    for(int i=0;i<toCheck.length();i++) {
    	if(toCheck.charAt(i) == '*') {counter++;}
    	}
    	sb.append(counter);
    	sb.append("\\n");
  }

  private void bottomSide(String[][] elements, StringBuilder sb, int y, StringBuilder toCheck,
      int counter) {
    toCheck.append(elements[elements.length-1][y-1]);
    toCheck.append(elements[elements.length-1][y+1]);
    toCheck.append(elements[elements.length-2][y-1]);
    toCheck.append(elements[elements.length-2][y]);
    toCheck.append(elements[elements.length-2][y+1]);
    for(int i=0;i<toCheck.length();i++) {
    	if(toCheck.charAt(i) == '*') {counter++;}
    	}
    	sb.append(counter);
  }

  private void leftSide(String[][] elements, StringBuilder sb, int x, int y, StringBuilder toCheck,
      int counter) {
    toCheck.append(elements[x-1][y]);
    toCheck.append(elements[x+1][y]);
    toCheck.append(elements[x-1][y+1]);
    toCheck.append(elements[x][y+1]);
    toCheck.append(elements[x+1][y+1]);
    for(int i=0;i<toCheck.length();i++) {
    	if(toCheck.charAt(i) == '*') {counter++;}
    }
    	sb.append(counter);
  }

  private void upperSide(String[][] elements, StringBuilder sb, int x, int y, StringBuilder toCheck,
      int counter) {
    toCheck.append(elements[x][y-1]);
    toCheck.append(elements[x][y+1]);
    toCheck.append(elements[x+1][y-1]);
    toCheck.append(elements[x+1][y]);
    toCheck.append(elements[x+1][y+1]);
    for(int i=0;i<toCheck.length();i++) {
    	if(toCheck.charAt(i) == '*') {counter++;}
    }
    	sb.append(counter);
  }

  private void rightBottomCorner(String[][] elements, StringBuilder sb, int x, StringBuilder toCheck,
      int counter) {
    toCheck.append(elements[elements.length-2][elements[x].length-1]);
    toCheck.append(elements[elements.length-2][elements[x].length-2]);
    toCheck.append(elements[elements.length-1][elements[x].length-2]);
    for(int i=0;i<toCheck.length();i++) {
    	if(toCheck.charAt(i) == '*') {counter++;}
    }
    	sb.append(counter);
  }

  private void rightUpperCorner(String[][] elements, StringBuilder sb, int y, StringBuilder toCheck,
      int counter) {
    toCheck.append(elements[elements.length-2][y]);
    toCheck.append(elements[elements.length-1][y+1]);
    toCheck.append(elements[elements.length-2][y+1]);
    for(int i=0;i<toCheck.length();i++) {
    	if(toCheck.charAt(i) == '*') {counter++;}	
    	}
    	sb.append(counter);
  }

  private void leftBottomCorner(String[][] elements, StringBuilder sb, int x, StringBuilder toCheck,
      int counter) {
    toCheck.append(elements[x][elements[x].length-2]);
    toCheck.append(elements[x+1][elements[x].length-2]);
    toCheck.append(elements[x+1][elements[x].length-1]);
    for(int i=0;i<toCheck.length();i++) {
    	if(toCheck.charAt(i) == '*') {counter++;}
    }
    	sb.append(counter);
    	sb.append("\\n");
  }

  private void clear(StringBuilder toCheck, int counter) {
    counter = 0;
    toCheck.replace(0,toCheck.length(),"");
  }

  private void leftUpperCorner(String[][] elements, StringBuilder sb, StringBuilder toCheck,
      int counter, int x, int y) {
    toCheck.append(elements[x][y+1]);
    toCheck.append(elements[x+1][y]);
    toCheck.append(elements[x+1][y+1]);
    for(int i=0;i<toCheck.length();i++) {
    	if(toCheck.charAt(i) == '*') {counter++;}
    	}
    	sb.append(counter);
  }
  
  
  
  
  
  
public String getHintField2() throws IllegalStateException {
      String[] rows = settedMineField.split("\n");
      String[][] elements = new String[rows.length][rows[1].length()];
      for(int i=0;i<rows.length;i++) {
          String[] split = rows[i].split("");
          for(int j=0;j<split.length;j++) {               
              elements[i][j] = split[j];
          }
      }
      
      StringBuilder sb = new StringBuilder();
 
      for(int x=0;x<elements.length;x++) {
          for(int y=0;y<elements[x].length;y++) { 
              if("*".equals(elements[x][y])) {
                  sb.append(elements[x][y]);
              }else
              if(x==0 && y==0) {
                    long count = Stream.of(elements[x][y+1],elements[x+1][y],elements[x+1][y+1])
                    .filter(e->"*".equals(e)).count();                 
                      sb.append(count);
              }else
              if(x==0 && y==elements[x].length-1) {
                long count = Stream.of(elements[x][elements[x].length-2],elements[x+1][elements[x].length-2],elements[x+1][elements[x].length-1])
                    .filter("*"::equals).count();
                      sb.append(count);
                      sb.append("\\n");
              }else           
              if(x==elements.length-1 && y==0) {
                long count = Stream.of(elements[elements.length-2][y],elements[elements.length-1][y+1],elements[elements.length-2][y+1])
                    .filter("*"::equals).count();
                      sb.append(count);       
              }else
              if(x==elements.length-1 && y==elements[x].length-1) {
                long count = Stream.of(elements[elements.length-2][elements[x].length-1],elements[elements.length-2][elements[x].length-2],elements[elements.length-1][elements[x].length-2])
                    .filter("*"::equals).count();
                      sb.append(count);
              }else
              if(x==0) {
                long count = Stream.of(elements[x][y-1],elements[x][y+1],elements[x+1][y-1],elements[x+1][y],elements[x+1][y+1])
                    .filter("*"::equals).count();
                      sb.append(count);
              }else
              if(y==0) {
                long count = Stream.of(elements[x-1][y],elements[x+1][y],elements[x-1][y+1],elements[x][y+1],elements[x+1][y+1])
                    .filter("*"::equals).count();
                      sb.append(count);          
              }else
              if(x==elements.length-1) {
                long count = Stream.of(elements[elements.length-1][y-1],elements[elements.length-1][y+1],elements[elements.length-2][y-1],
                    elements[elements.length-2][y],elements[elements.length-2][y+1])
                    .filter("*"::equals).count();
                      sb.append(count);
              }else   
              if(y==elements[x].length-1) {
                long count = Stream.of(elements[x-1][elements[x].length-1],elements[x+1][elements[x].length-1],
                    elements[x-1][elements[x].length-2],elements[x][elements[x].length-2],elements[x+1][elements[x].length-2])
                    .filter("*"::equals).count();
                      sb.append(count);
                      sb.append("\\n");
              }
              else {
                long count = Stream.of(elements[x-1][y-1],
                    elements[x-1][y],
                    elements[x-1][y+1],
                    elements[x][y-1],
                    elements[x][y+1],
                    elements[x+1][y],
                    elements[x+1][y+1],
                    elements[x+1][y+1])
                    .filter("*"::equals).count();
                      sb.append(count);              
              }
          }
      }   
      
      return sb.toString();
	}
}
