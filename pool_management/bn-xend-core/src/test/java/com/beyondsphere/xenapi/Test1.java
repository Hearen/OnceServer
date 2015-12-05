package com.beyondsphere.xenapi;

public class Test1 {
	public static void main(String[] args) {
		boolean is_loop = false;
		if (args.length>0){
			for(int i=0;i<args.length;i++){
				System.out.println(args[i]);
				if (args[i].equals("l")){
					System.out.println("exec");
					is_loop = true;
				}
			}
		}
		while (true){
			loop();
			try{
				Thread.sleep(2000);
			}catch (InterruptedException e){
				e.printStackTrace();
			}
			if (!is_loop){
				break;
			}
			
		}
	}
	
	public static void loop(){
		System.out.println("hello");
	}
}

