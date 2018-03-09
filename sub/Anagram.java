import java.util.Scanner;
import java.io.*;
import java.lang.Math;
import java.util.Vector;
import java.lang.String;
import java.util.Arrays;
import java.util.Collections;
import java.util.*;
import java.lang.*;

/*
size of vocab <= 40000
size of input string <= 12
size of every word in vocab >=3
*/

class node{
	String str;
	node next;
	public node(String a,node n){
		str = a;
		next = n;
	}
}

public class Anagram{

	private static String sort(String s){
		char arr[] = s.toCharArray();
		Arrays.sort(arr);
		return new String(arr);
	}

	private static boolean isPrime(int p){
		int i=2;
		double r = Math.sqrt(p);

		if(r == (double)(int)r)
			return false;

		while(i<=r && p%i!=0)
			i++;

		return i>r;
	}

	private static int ascii(char c){
		int i=(int)c;
		if(i>=97 && i<=122)
			return i-87;
		else if(i==39)
			return 36;
		else if(i>=48 && i<=57)
			return i;
		else
			return 36;
	}

	private static int hash1(String str,int t){//t = tablesize
		int l=str.length(),r=37,val=0;
		for(int i=0;i<l;i++){
			val = (val*r + ascii(str.charAt(i)) )%t;
		}

		return val;
	}

	/*private static int hash2(String str,int t){//t = tablesize
		int l=str.length(),r=117,val=0;
		for(int i=0;i<l;i++){
			val = (val*r + ascii(str.charAt(i)) )%t;//experiment by changing r, taking ascii() instead of direct ascii value.
		}
		val++;
		while(t%val==0)
			val++;
		
		return  val%t;
	}*/

	private static Vector<String> fun1(String a,int p,node[] arr){
		Vector<String> v = new Vector<>();
		String b = sort(a);
		int h1 = hash1(b,p);
		node temp=arr[h1];

		while(temp!=null){
			if(sort(temp.str).equals(b)){
				v.add(temp.str);
			}

			temp = temp.next;
		}

		return v;
	}

	private static Vector<String> fun2(String s,int p,node[] arr){//returns all anagrams of given string with 1 space in a vector
		Vector<String> varr = new Vector<String>();
		Vector<String> v1 = new Vector<String>();
		Vector<String> v2 = new Vector<String>();
		Vector<String> v = new Vector<String>();		
		
		int l = s.length();
		int a,b,c,d,e,f,k,z,l1,l2;
		String s1,s2,s3;

		if(l>=6){
			for(a=0;a<l-2;a++){
				for(b=a+1;b<l-1;b++){
					for(c=b+1;c<l;c++){
						s1="";
						s2="";
						for(k=0;k<l;k++){
							if(k==a ||k==b||k==c)
								s1+=s.charAt(k);
							else
								s2+=s.charAt(k);
						}

						v1 = fun1(s1,p,arr);
						if(v1.size()>0){	
							v2 = fun1(s2,p,arr);

							l1=v1.size();
							l2=v2.size();
							if(l1>0 && l2>0){
								for(k=0;k<l1;k++){
									for(z=0;z<l2;z++){
										s3 = v1.get(k) + " " + v2.get(z);
										varr.add(s3);
										if(l>6){
											s3 = v2.get(z) + " " + v1.get(k);
											varr.add(s3);
										}
									}
								}
							}
						}
					}
				}
			}

		}
		if(l>=8){
			for(a=0;a<l-3;a++){
				for(b=a+1;b<l-2;b++){
					for(c=b+1;c<l-1;c++){
						for(d=c+1;d<l;d++){
							s1="";
							s2="";
							for(k=0;k<l;k++){
								if(k==a ||k==b||k==c ||k==d)
									s1+=s.charAt(k);
								else
									s2+=s.charAt(k);
							}

							//System.out.println("s1 = " + s1 + "\ts2 = " + s2);

							v1 = fun1(s1,p,arr);
							if(v1.size()>0){	
								v2 = fun1(s2,p,arr);

								l1=v1.size();
								l2=v2.size();
								if(l1>0 && l2>0){
									for(k=0;k<l1;k++){
										for(z=0;z<l2;z++){
											s3 = v1.get(k) + " " + v2.get(z);
											varr.add(s3);
											if(l>8){
												s3 = v2.get(z) + " " + v1.get(k);
												varr.add(s3);
											}
										}
									}
								}
							}
						}
					}
				}
			}

			//Collections.sort(varr[1]);
		}
		if(l>=10){

			for(a=0;a<l-4;a++){
				for(b=a+1;b<l-3;b++){
					for(c=b+1;c<l-2;c++){
						for(d=c+1;d<l-1;d++){
							for(e=d+1;e<l;e++){		
								s1="";
								s2="";
								for(k=0;k<l;k++){
									if(k==a ||k==b||k==c ||k==d || k==e)
										s1+=s.charAt(k);
									else
										s2+=s.charAt(k);
								}
								//System.out.println("s1 = " + s1 + "\ts2 = " + s2);

								v1 = fun1(s1,p,arr);
								if(v1.size()>0){	
									v2 = fun1(s2,p,arr);

									l1=v1.size();
									l2=v2.size();
									if(l1>0 && l2>0){
										for(k=0;k<l1;k++){
											for(z=0;z<l2;z++){
												s3 = v1.get(k) + " " + v2.get(z);
												varr.add(s3);
												if(l>10){
													s3 = v2.get(z) + " " + v1.get(k);
													varr.add(s3);
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}

			//Collections.sort(varr[2]);
		}
		if(l>=12){

			for(a=0;a<l-5;a++){
				for(b=a+1;b<l-4;b++){
					for(c=b+1;c<l-3;c++){
						for(d=c+1;d<l-2;d++){
							for(e=d+1;e<l-1;e++){		
								for(f=e+1;f<l;f++){
									s1="";
									s2="";
									for(k=0;k<l;k++){
										if(k==a ||k==b||k==c ||k==d || k==e || k==f)
											s1+=s.charAt(k);
										else
											s2+=s.charAt(k);
									}
									//System.out.println("s1 = " + s1 + "\ts2 = " + s2);

									v1 = fun1(s1,p,arr);
									if(v1.size()>0){
										v2 = fun1(s2,p,arr);

										l1=v1.size();
										l2=v2.size();
										if(l1>0 && l2>0){
											for(k=0;k<l1;k++){
												for(z=0;z<l2;z++){
													s3 = v1.get(k) + " " + v2.get(z);
													varr.add(s3);
													if(l>12){
														s3 = v2.get(z) + " " + v1.get(k);
														varr.add(s3);
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}

			//Collections.sort(varr[3]);
		}

		l=varr.size();
		if(l>0){
			Collections.sort(varr);
		// k=0;
		// while(true){//removing duplicates in varr
		// 	while(k<varr.size()-1 && varr.get(k).equals(varr.get(k+1)))
		// 		varr.remove(k);
		// 	k++;
		// 	if(k>=varr.size()-1)
		// 		break;
		// }

			v.add(varr.get(0));
			a=0;
			for(k=0;k<l;k++){
				if(varr.get(k).equals(v.get(a))==false){
					a++;
					v.add(varr.get(k));
				}
			}

		}

		/*if(varr.size()==0)
			System.out.println("varr is empty in fun2");
		*/
		return v;
	}

	private static Vector<String> fun3(String s,int p,node[] arr){
		Vector<String> varr = new Vector<String>();
		Vector<String> v1 = new Vector<String>();
		Vector<String> v2 = new Vector<String>();
		Vector<String> v3 = new Vector<String>();
		Vector<String> vf = new Vector<String>();

		int l = s.length();
		int a,b,c,d,e,f,g,h,j,k,z,l1,l2,l3;
		String s1,s2,s3,s4;

		if(l==9){
			for(a=0;a<l-2;a++){//3,3,3 case
				for(b=a+1;b<l-1;b++){
					for(c=b+1;c<l;c++){
						s1="";
						s2="";
						for(k=0;k<l;k++){
							if(k==a ||k==b||k==c)
								s1+=s.charAt(k);
							else
								s2+=s.charAt(k);
						}
						//System.out.println("in 3,a case s1 = " + s1 + "\ts2 = " + s2);

						v1 = fun1(s1,p,arr);
						if(v1.size()>0){	
							v2 = fun2(s2,p,arr);

							l1=v1.size();
							l2=v2.size();
							if(l1>0 && l2>0){
								for(k=0;k<l1;k++){
									for(z=0;z<l2;z++){
										s1=v1.get(k);
										s2=v2.get(z);
										s4 = s1 + " " + s2;
										varr.add(s4);
									}
								}
							}
						}
					}
				}
			}
		}
		else if(l==10){
			for(a=0;a<l-3;a++){//3,3,4 case
				for(b=a+1;b<l-2;b++){
					for(c=b+1;c<l-1;c++){
						for(d=c+1;d<l;d++){	
							s1="";
							s2="";
							for(k=0;k<l;k++){
								if(k==a ||k==b||k==c || k==d)
									s1+=s.charAt(k);
								else
									s2+=s.charAt(k);
							}
							//System.out.println("in 3,a case s1 = " + s1 + "\ts2 = " + s2);

							v1 = fun1(s1,p,arr);
							if(v1.size()>0){	
								v2 = fun2(s2,p,arr);

								l1=v1.size();
								l2=v2.size();
								if(l1>0 && l2>0){
									for(k=0;k<l1;k++){
										for(z=0;z<l2;z++){
											s1=v1.get(k);
											s2=v2.get(z);
											//System.out.println("in string " + s2 + " found space at index " + j);
											s4 = s1 + " " + s2;
											varr.add(s4);
											s4 = s2 + " " + s1;
											varr.add(s4);

											j=s2.indexOf(' ');
											s3 = s2.substring(j+1);
											s2 = s2.substring(0,j);
											
											s4 = s2 + " " + s1 + " " + s3;
											varr.add(s4);
										}
									}
								}
							}
						}
					}
				}
			}
		}
		else if(l==11){
			for(a=0;a<l-4;a++){//3,3,5 case
				for(b=a+1;b<l-3;b++){
					for(c=b+1;c<l-2;c++){
						for(d=c+1;d<l-1;d++){	
							for(e=d+1;e<l;e++){	
								s1="";
								s2="";
								for(k=0;k<l;k++){
									if(k==a ||k==b||k==c || k==d || k==e)
										s1+=s.charAt(k);
									else
										s2+=s.charAt(k);
								}
								//System.out.println("in 3,a case s1 = " + s1 + "\ts2 = " + s2);

								v1 = fun1(s1,p,arr);
								if(v1.size()>0){	
									v2 = fun2(s2,p,arr);

									l1=v1.size();
									l2=v2.size();
									if(l1>0 && l2>0){
										for(k=0;k<l1;k++){
											for(z=0;z<l2;z++){
												s1=v1.get(k);
												s2=v2.get(z);
												//System.out.println("in string " + s2 + " found space at index " + j);
												s4 = s1 + " " + s2;
												varr.add(s4);
												s4 = s2 + " " + s1;
												varr.add(s4);

												j=s2.indexOf(' ');
												s3 = s2.substring(j+1);
												s2 = s2.substring(0,j);
												
												s4 = s2 + " " + s1 + " " + s3;
												varr.add(s4);
											}
										}
									}
								}
							}
						}
					}
				}
			}

			for(a=0;a<l-2;a++){//3,4,4 case
				for(b=a+1;b<l-1;b++){
					for(c=b+1;c<l;c++){
						s1="";
						s2="";
						for(k=0;k<l;k++){
							if(k==a ||k==b||k==c)
								s1+=s.charAt(k);
							else
								s2+=s.charAt(k);
						}
						//System.out.println("in 3,a case s1 = " + s1 + "\ts2 = " + s2);

						v1 = fun1(s1,p,arr);
						if(v1.size()>0){
							v2 = fun2(s2,p,arr);

							l1=v1.size();
							l2=v2.size();
							if(l1>0 && l2>0){
								for(k=0;k<l1;k++){
									for(z=0;z<l2;z++){
										s1=v1.get(k);
										s2=v2.get(z);
										//System.out.println("in string " + s2 + " found space at index " + j);
										s4 = s1 + " " + s2;
										varr.add(s4);
										s4 = s2 + " " + s1;
										varr.add(s4);

										j=s2.indexOf(' ');
										s3 = s2.substring(j+1);
										s2 = s2.substring(0,j);
										
										s4 = s2 + " " + s1 + " " + s3;
										varr.add(s4);
									}
								}
							}
						}
					}
				}
			}
		}
		else if (l==12) {
			
			for(a=0;a<l-3;a++){//4,4,4 case
				for(b=a+1;b<l-2;b++){
					for(c=b+1;c<l-1;c++){
						for(d=c+1;d<l;d++){	
							s1="";
							s2="";
							for(k=0;k<l;k++){
								if(k==a ||k==b||k==c||k==d)
									s1+=s.charAt(k);
								else
									s2+=s.charAt(k);
							}
							//System.out.println("in 3,a case s1 = " + s1 + "\ts2 = " + s2);

							v1 = fun1(s1,p,arr);
							if(v1.size()>0){
								v2 = fun2(s2,p,arr);

								l1=v1.size();
								l2=v2.size();
								if(l1>0 && l2>0){
									for(k=0;k<l1;k++){
										for(z=0;z<l2;z++){
											s1=v1.get(k);
											s2=v2.get(z);
											s4 = s1 + " " + s2;
											varr.add(s4);
										}
									}
								}
							}
						}
					}
				}
			}

			for(a=0;a<l-5;a++){//3,3,6 case
				for(b=a+1;b<l-4;b++){
					for(c=b+1;c<l-3;c++){
						for(d=c+1;d<l-2;d++){	
							for(e=d+1;e<l-1;e++){	
								for(f=e+1;f<l;f++){	
									s1="";
									s2="";
									for(k=0;k<l;k++){
										if(k==a ||k==b||k==c || k==d || k==e||k==f)
											s1+=s.charAt(k);
										else
											s2+=s.charAt(k);
									}
									//System.out.println("in 3,a case s1 = " + s1 + "\ts2 = " + s2);

									v1 = fun1(s1,p,arr);
									if(v1.size()>0){	
										v2 = fun2(s2,p,arr);

										l1=v1.size();
										l2=v2.size();
										if(l1>0 && l2>0){
											for(k=0;k<l1;k++){
												for(z=0;z<l2;z++){
													s1=v1.get(k);
													s2=v2.get(z);
													//System.out.println("in string " + s2 + " found space at index " + j);
													s4 = s1 + " " + s2;
													varr.add(s4);
													s4 = s2 + " " + s1;
													varr.add(s4);

													j=s2.indexOf(' ');
													s3 = s2.substring(j+1);
													s2 = s2.substring(0,j);
													
													s4 = s2 + " " + s1 + " " + s3;
													varr.add(s4);
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}

			for(a=0;a<l-2;a++){//3,4,5 case
				for(b=a+1;b<l-1;b++){
					for(c=b+1;c<l;c++){
						s1="";
						s2="";
						for(k=0;k<l;k++){
							if(k==a ||k==b||k==c)
								s1+=s.charAt(k);
							else
								s2+=s.charAt(k);
						}
						//System.out.println("in 3,a case s1 = " + s1 + "\ts2 = " + s2);

						v1 = fun1(s1,p,arr);
						if(v1.size()>0){	
							v2 = fun2(s2,p,arr);

							l1=v1.size();
							l2=v2.size();
							if(l1>0 && l2>0){
								for(k=0;k<l1;k++){
									for(z=0;z<l2;z++){
										s1=v1.get(k);
										s2=v2.get(z);
										
										s4 = s1 + " " + s2;
										varr.add(s4);
										s4 = s2 + " " + s1;
										varr.add(s4);

										j=s2.indexOf(' ');
										//System.out.println("in string " + s2 + " found space at index " + j);
										s3 = s2.substring(j+1);
										s2 = s2.substring(0,j);

										s4 = s2 + " " + s1 + " " + s3;
										varr.add(s4);
									}
								}
							}
						}
					}
				}
			}
		}

		// Collections.sort(varr);
		// k=0;
		// while(true){//removing duplicates in varr
		// 	while(k<varr.size()-1 && varr.get(k).equals(varr.get(k+1)))
		// 		varr.remove(k);
		// 	k++;
		// 	if(k>=varr.size()-1)
		// 		break;
		// }

		l=varr.size();
		if(l>0){
			Collections.sort(varr);
		// k=0;
		// while(true){//removing duplicates in varr
		// 	while(k<varr.size()-1 && varr.get(k).equals(varr.get(k+1)))
		// 		varr.remove(k);
		// 	k++;
		// 	if(k>=varr.size()-1)
		// 		break;
		// }

			vf.add(varr.get(0));
			a=0;
			for(k=0;k<l;k++){
				if(varr.get(k).equals(vf.get(a))==false){
					a++;
					vf.add(varr.get(k));
				}
			}

		}

		return vf;
	}

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();

		try{
			FileInputStream fstream = new FileInputStream(args[0]);
			Scanner s = new Scanner(fstream);
			FileInputStream fstream2 = new FileInputStream(args[1]);
			Scanner s2 = new Scanner(fstream2);
		
			int n = s.nextInt(),n2=s2.nextInt();
			//System.out.println("n = " + n + "\tn2 = " + n2);
			int h1,h2,j,l,p=n+1;//so that there is atleast 1 empty block
			String a,b;
			node temp;
			Vector<String> v1 = new Vector<>();
			//Vector<String> v2 = new Vector<>();
			//Vector<String> v3 = new Vector<>();
			

			while(isPrime(p)==false)
					p++;
			
			node arr[] = new node[p];
			/*while(s2.hasNextLine())
				System.out.println(s2.nextLine());*/

				s.nextLine();
			for(int i=0;i<n;i++){//hashing vocab to table
				a = s.nextLine();
				
				/*if(i<20){
					System.out.println(i + " a = " + a);
				}*/

				b=sort(a);
				h1 = hash1(b,p);

				arr[h1] = new node(a,arr[h1]);

			}
			
			s2.nextLine();
			for(int i=0;i<n2;i++){
				a = s2.nextLine();
	
				/*if(i<20){
					System.out.println((i+1) + " a = " + a);
				}*/
	
				l=a.length();

				if(l>=3 && l<=12){//no spaces
					
					v1 = fun1(a,p,arr);
				}

				if(l>=6 && l<=12){//1 space
					v1.addAll(fun2(a,p,arr));
				}
		// 		long endTime2 = System.currentTimeMillis();
		// System.out.println("Total time = " + (endTime2 - startTime))	;
		 		
		 		if(l>=9 && l<=12){//2 spaces
					//System.out.println("calling fun3 for " + a);
					v1.addAll(fun3(a,p,arr));
				}
		// 		long endTime3 = System.currentTimeMillis();
		// System.out.println("Total time = " + (endTime3 - startTime))	;

				Collections.sort(v1);
				int k=0; 
				while(true){//removing duplicates in v1
					while(k<v1.size()-1 && v1.get(k).equals(v1.get(k+1)))
						v1.remove(k);
					k++;
					if(k>=v1.size()-1)
						break;
				}

				//System.out.println("no. of words = " + v1.size() );

				while(v1.isEmpty()==false){
					System.out.println(v1.get(0));
					v1.remove(0);
				}

				System.out.println("-1");
			}


			//System.out.println("no. of words = " + v1.size() );
		}
		catch(FileNotFoundException e){
			System.out.println(e);
		}

		/*long endTime = System.currentTimeMillis();
		System.out.println("Total time = " + (endTime - startTime))	;
		*/
	}
}