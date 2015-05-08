import java.util.ArrayList;

class base{
	int base64encode (ArrayList<Character> b,ArrayList<Character> c,int size){
		int i,j=0,tmpa,tmpb=0,tmpc=0,tmpd;
		boolean flag=true;
		int tmp=0,sizea=0,k,l=0;
		ArrayList<Character> d = new ArrayList<Character>();
		
		if(size<=0) return -1;
		l=(3-size%3)%3;
		if((size*4)%3!=0) tmpd = 1;
		else tmpd = 0;
		size=(size*4/3)+tmpd;
			
			for(i=0;i<size;i++){
				//if(i%4==0){
					//tmpb=0;tmpa=0;tmpc=0;tmp=0;
					//}
				 if(sizea==size) break;
						if(i%4==0){
							try{
								tmpa=(int)((b.get(i+tmp))%4);
							}
							catch(java.lang.IndexOutOfBoundsException e){
								tmpa=0;
							}
							try{
								d.add((char)((b.get(i+tmp)-tmpa)/4));
							}
							catch(java.lang.IndexOutOfBoundsException e){
								d.add((char)0);
							}
							tmpb=tmpa;
						}
						else if(i%4==1){
							try{
								tmpa=b.get(i+tmp)%16;
							}
							catch(java.lang.IndexOutOfBoundsException e){
								tmpa=0;
							}
							try{
								d.add((char)(((b.get(i+tmp)-tmpa)/16)+tmpb*16));
							}
							catch(java.lang.IndexOutOfBoundsException e){
								d.add((char)(tmpb*16));
							}
							tmpb=tmpa;
						}
						else if(i%4==2){
							try{
								tmpa=b.get(i+tmp)%64;
							}
							catch(java.lang.IndexOutOfBoundsException e){
								tmpa=0;
							}
							try{
								d.add((char)(((b.get(i+tmp)-tmpa)/64)+tmpb*4));
							}
							catch(java.lang.IndexOutOfBoundsException e){
								d.add((char)(tmpb*4));
							}
							tmpc=tmpa;
						}
						else if(i%4==3){
							d.add((char)tmpc);
							tmp--;
						}
							sizea++;
			  }
		
			  sizea=0;
			  for(i=0;i<size;i++){
				  if(sizea==size) break;
				  if(d.get(i)==63) c.add('/');
				  else if(d.get(i)==62) c.add('+');
				  else if(d.get(i)>51) c.add((char)(d.get(i)-4));
				  else if(d.get(i)<26 &&d.get(i)>-1) c.add((char)(d.get(i)+65));
				  else if(d.get(i)<52 &&d.get(i)>25) c.add((char)(d.get(i)+71));
				  sizea++;
				  //System.out.println(size);
		                  //C[i][j]=Åf\rÅf;
				  //if(i%72==0)c.add('\n');
		  }
		
			for(k=0;k<l;k++){
				c.add('=');
				//System.out.println(k);
				//c[i-1][j+k]='=';
				size++;
			}
			//if((i+k)%72==0)c.add('\n');
			//c[i-1][j+k]='\n';
			System.out.println(i+k+"ï∂éö");
			b.clear();
			d.clear();
			return i+k;
		}
	int base64encode (ArrayList<Character> b,ArrayList<Character> c,int size,int cr){
	int i,j=0,tmpa,tmpb=0,tmpc=0,tmpd;
	boolean flag=true;
	int tmp=0,sizea=0,k,l=0,crlf=0;
	ArrayList<Character> d = new ArrayList<Character>();
	
	if(cr <= 0) flag=false;
	if(size<=0) return -1;
	l=(3-size%3)%3;
	if((size*4)%3!=0) tmpd = 1;
	else tmpd = 0;
	size=(size*4/3)+tmpd;
		
		for(i=0;i<size;i++){
			//if(i%4==0){
				//tmpb=0;tmpa=0;tmpc=0;tmp=0;
				//}
			 if(sizea==size) break;
					if(i%4==0){
						try{
							tmpa=(int)((b.get(i+tmp))%4);
						}
						catch(java.lang.IndexOutOfBoundsException e){
							tmpa=0;
						}
						try{
							d.add((char)((b.get(i+tmp)-tmpa)/4));
						}
						catch(java.lang.IndexOutOfBoundsException e){
							d.add((char)0);
						}
						tmpb=tmpa;
					}
					else if(i%4==1){
						try{
							tmpa=b.get(i+tmp)%16;
						}
						catch(java.lang.IndexOutOfBoundsException e){
							tmpa=0;
						}
						try{
							d.add((char)(((b.get(i+tmp)-tmpa)/16)+tmpb*16));
						}
						catch(java.lang.IndexOutOfBoundsException e){
							d.add((char)(tmpb*16));
						}
						tmpb=tmpa;
					}
					else if(i%4==2){
						try{
							tmpa=b.get(i+tmp)%64;
						}
						catch(java.lang.IndexOutOfBoundsException e){
							tmpa=0;
						}
						try{
							d.add((char)(((b.get(i+tmp)-tmpa)/64)+tmpb*4));
						}
						catch(java.lang.IndexOutOfBoundsException e){
							d.add((char)(tmpb*4));
						}
						tmpc=tmpa;
					}
					else if(i%4==3){
						d.add((char)tmpc);
						tmp--;
					}
						sizea++;
		  }
	
		  sizea=0;
		  for(i=0;i<size;i++){
			  if(sizea==size) break;
			  if(d.get(i)==63) c.add('/');
			  else if(d.get(i)==62) c.add('+');
			  else if(d.get(i)>51) c.add((char)(d.get(i)-4));
			  else if(d.get(i)<26 &&d.get(i)>-1) c.add((char)(d.get(i)+65));
			  else if(d.get(i)<52 &&d.get(i)>25) c.add((char)(d.get(i)+71));
			  if(flag)if(cr == 1 && i == 0){
				  c.add('\n');
				  crlf++;
			  }
			  if(flag)if((i+1)%cr== 0 && i != 0){
				  c.add('\n');
				  crlf++;
			  }
			  sizea++;
			  //System.out.println(size);
	                  //C[i][j]=Åf\rÅf;
			  //if(i%72==0)c.add('\n');
	  }
	
		for(k=0;k<l;k++){
			c.add('=');
			  if(flag)if((i+k+1)%cr== 0){
				  c.add('\n');
				  crlf++;
			  }
			//System.out.println(k);
			//c[i-1][j+k]='=';
			size++;
		}
		//if((i+k)%72==0)c.add('\n');
		//c[i-1][j+k]='\n';
		System.out.println(c.size()+"byte");
		System.out.println(crlf+"â¸çs");
		System.out.println((c.size()-crlf)+"ï∂éö");
		b.clear();
		d.clear();
		return c.size();
	}
	int base64encode (ArrayList<Character> b,int size,int cr,StringBuilder bsb){
		int i,j=0,tmpa,tmpb=0,tmpc=0,tmpd;
		boolean flag=true;
		int tmp=0,sizea=0,k,l=0,crlf=0;
		StringBuilder lf = new StringBuilder(System.getProperty("line.separator"));
		ArrayList<Character> d = new ArrayList<Character>();
		
		if(cr <= 0) flag=false;
		if(size<=0) return -1;
		l=(3-size%3)%3;
		if((size*4)%3!=0) tmpd = 1;
		else tmpd = 0;
		size=(size*4/3)+tmpd;
			
			for(i=0;i<size;i++){
				//if(i%4==0){
					//tmpb=0;tmpa=0;tmpc=0;tmp=0;
					//}
				 if(sizea==size) break;
						if(i%4==0){
							try{
								tmpa=(int)((b.get(i+tmp))%4);
							}
							catch(java.lang.IndexOutOfBoundsException e){
								tmpa=0;
							}
							try{
								d.add((char)((b.get(i+tmp)-tmpa)/4));
							}
							catch(java.lang.IndexOutOfBoundsException e){
								d.add((char)0);
							}
							tmpb=tmpa;
						}
						else if(i%4==1){
							try{
								tmpa=b.get(i+tmp)%16;
							}
							catch(java.lang.IndexOutOfBoundsException e){
								tmpa=0;
							}
							try{
								d.add((char)(((b.get(i+tmp)-tmpa)/16)+tmpb*16));
							}
							catch(java.lang.IndexOutOfBoundsException e){
								d.add((char)(tmpb*16));
							}
							tmpb=tmpa;
						}
						else if(i%4==2){
							try{
								tmpa=b.get(i+tmp)%64;
							}
							catch(java.lang.IndexOutOfBoundsException e){
								tmpa=0;
							}
							try{
								d.add((char)(((b.get(i+tmp)-tmpa)/64)+tmpb*4));
							}
							catch(java.lang.IndexOutOfBoundsException e){
								d.add((char)(tmpb*4));
							}
							tmpc=tmpa;
						}
						else if(i%4==3){
							d.add((char)tmpc);
							tmp--;
						}
							sizea++;
			  }
			b.clear();
			  sizea=0;
			  for(i=0;i<size;i++){
				  if(sizea==size) break;
				  if(d.get(i)==63) bsb.append('/');
				  else if(d.get(i)==62) bsb.append('+');
				  else if(d.get(i)>51) bsb.append((char)(d.get(i)-4));
				  else if(d.get(i)<26 &&d.get(i)>-1) bsb.append((char)(d.get(i)+65));
				  else if(d.get(i)<52 &&d.get(i)>25) bsb.append((char)(d.get(i)+71));
				  if(flag)if(cr == 1 && i == 0){
					  bsb.append(lf);
					  crlf++;
				  }
				  if(flag)if((i+1)%cr== 0 && i != 0){
					  bsb.append(lf);
					  crlf++;
				  }
				  sizea++;
				  //System.out.println(size);
		                  //C[i][j]=Åf\rÅf;
				  //if(i%72==0)c.add('\n');
				  //if(i%100000 == 0) System.out.println(i);
				  //if(i>6200000) System.out.println(i);
		  }
			 
			for(k=0;k<l;k++){
				bsb.append('=');
				  if(flag)if((i+k+1)%cr== 0){
					  bsb.append(lf);
					  crlf++;
				  }
				//System.out.println(k);
				//c[i-1][j+k]='=';
				size++;
			}
			//if((i+k)%72==0)c.add('\n');
			//c[i-1][j+k]='\n';
			System.out.println(bsb.length()+"byte");
			System.out.println(crlf*System.getProperty("line.separator").length()+"â¸çs");
			System.out.println((bsb.length()-crlf*System.getProperty("line.separator").length())+"ï∂éö");
			d.clear();
			return bsb.length();
		}
	int base64encode (char[] b,char[] c,int size,int l){
		int i,j=0,tmpa,tmpb=0,tmpc=0,tmpd;
		boolean flag=true;
		int tmp=0,sizea=0,k;
		
		
		/*if(size<=0) return -1;
		l=(3-size%3)%3;
		if((size*4)%3!=0) tmpd = 1;
		else tmpd = 0;
		size=(size*4/3)+tmpd;*/
		char[] d =null;// new char[size];
		try{d = new char[size];}
		catch(OutOfMemoryError e){
			System.out.println("à»â∫ÉGÉâÅ[ì‡óe");
			e.printStackTrace();
			return -1;}
			for(i=0;i<size;i++){
				//if(i%4==0){
					//tmpb=0;tmpa=0;tmpc=0;tmp=0;
					//}
				 if(sizea==size) break;
						if(i%4==0){
							try{
								tmpa=(int)((b[i+tmp])%4);
							}
							catch(java.lang.IndexOutOfBoundsException e){
								tmpa=0;
							}
							try{
								d[i] = ((char)((b[i+tmp]-tmpa)/4));
							}
							catch(java.lang.IndexOutOfBoundsException e){
								d[i] = ((char)0);
							}
							tmpb=tmpa;
						}
						else if(i%4==1){
							try{
								tmpa=b[i+tmp]%16;
							}
							catch(java.lang.IndexOutOfBoundsException e){
								tmpa=0;
							}
							try{
								d[i] = ((char)(((b[i+tmp]-tmpa)/16)+tmpb*16));
							}
							catch(java.lang.IndexOutOfBoundsException e){
								d[i] = ((char)(tmpb*16));
							}
							tmpb=tmpa;
						}
						else if(i%4==2){
							try{
								tmpa=b[i+tmp]%64;
							}
							catch(java.lang.IndexOutOfBoundsException e){
								tmpa=0;
							}
							try{
								d[i] = ((char)(((b[i+tmp]-tmpa)/64)+tmpb*4));
							}
							catch(java.lang.IndexOutOfBoundsException e){
								d[i] = ((char)(tmpb*4));
							}
							tmpc=tmpa;
						}
						else if(i%4==3){
							d[i] = ((char)tmpc);
							tmp--;
						}
							sizea++;
			  }
		
			  sizea=0;
			  for(i=0;i<size;i++){
				  if(sizea==size) break;
				  if(d[i]==63) c[i] = ('/');
				  else if(d[i]==62) c[i] = ('+');
				  else if(d[i]>51) c[i] = ((char)(d[i]-4));
				  else if(d[i]<26 &&d[i]>-1) c[i] = ((char)(d[i]+65));
				  else if(d[i]<52 &&d[i]>25) c[i] = ((char)(d[i]+71));
				  sizea++;
				  //System.out.println(size);
		                  //C[i][j]=Åf\rÅf;
				  //if(i%72==0)c.add('\n');
		  }
		
			for(k=0;k<l;k++){
				c[i+k] = ('=');
				//System.out.println(k);
				//c[i-1][j+k]='=';
				size++;
			}
			//if((i+k)%72==0)c.add('\n');
			//c[i-1][j+k]='\n';
			System.out.println(i+k+"ï∂éö");
			return i+k;
		}
	
	/*void kaigyou(ArrayList<Character> in,int size,int lf){
		int a=0;
		for(int i=0;i<=(size/lf);i++){
			for(int j = 0;j<lf;j++){
				try{
					System.out.print(in.get(j+i*lf)+"");
					}
				catch(java.lang.IndexOutOfBoundsException e){
					a=1;
					break;
					}
			}
			if(a==0) System.out.println();
		}
		
	}*/
}