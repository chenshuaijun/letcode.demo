package cn.letcode.demo.ta;

public class BubbleSort {
	public static void main(String[] args) {
		int score[] = getIntScore();
		for (int i = 0; i < score.length - 1; i++) {
			// �����n-1������
			for (int j = 0; j < score.length - i - 1; j++) {
				// �Ե�ǰ��������score[0......length-i-1]��������(j�ķ�Χ�ܹؼ��������Χ��������С��)
				if (score[j] < score[j + 1]) {
					// ��С��ֵ����������
					int temp = score[j];
					score[j] = score[j + 1];
					score[j + 1] = temp;
				}
			}
			System.out.print("��" + (i + 1) + "����������");
			for (int a = 0; a < score.length; a++) {
				System.out.print(score[a] + " ");
			}
			System.out.println("");
		}
		System.out.print("������������");
		for (int a = 0; a < score.length; a++) {
			System.out.print(score[a] + " ");
		}
	}

	public static int[] getIntScore() {
		int score[] = new int[512];
		for (int i = 0; i < 512; i++) {
			score[i] = (int) Math.round(1);
		}
		return score;
	}

}