public class InforEncry {

	public static byte[] jiaMi(byte[] b) {
		byte a;
		for (int i = 0; i < b.length / 2; i++) {
			a = b[i];
			b[i] = b[b.length - 1 - i];
			b[b.length - 1 - i] = a;
		}
		return b;
	}

	public static String jieMi(byte[] b, int length) {
		System.out.print("½èqing" + new String(b));
		byte a;
		for (int i = 0; i < length / 2; i++) {
			a = b[i];
			b[i] = b[length - 1 - i];
			b[length - 1 - i] = a;
		}
		return new String(b,0,length);
	}
}
