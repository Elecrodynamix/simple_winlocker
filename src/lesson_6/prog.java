package lesson_6;

import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;

public class prog {

	public static void main(String[] args) {
		new myFrame();
	}
}

class myFrame extends JFrame
{
	//переменная типа robot
	private Robot rob;
	//переменная типа Таймер
	private Timer tm;
	//Счетчик скриншотов
	private int kol=0;
	//Окно для блокировки
	private Frame wnd;
	

//конструктор класса
public myFrame()
{
	try
	{
			//создаем новый обьект класса Robot
		rob = new Robot();
		
	}
	catch (Exception e) {}
	
	//создаем таймер с задержкой в 10 секунд
	tm = new Timer(10000,new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			//снимаем скриншот экрана и записываем его в файл
			saveScreen();
		}
	});
	//запускаем таймер
	tm.start();
	
	//НЕ завершать работу приложения при закрытии окна
	setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	//выводим окно
	setVisible(true);
	//и сразу его прячем
	setVisible(false);
	
		}
private void saveScreen()
{
	
	//считаем колво скриншотов
	kol++;
	//определяем текущее разрешение экрана
	Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
	int w = dm.width;
	int h = dm.height;
	
	try
	{
		//снимает скриншот экрана с помощью класса Robot
		BufferedImage img = rob.createScreenCapture(new Rectangle(0,0,w,h));
		//сохраняем изображение в png файле
		ImageIO.write(img, "PNG", new File("c:\\java\\img"+kol+".png"));
	}
	catch (Exception e) {}
	if (kol==6)
	{
		//таймер останавливаем
		tm.stop();
		//создаем окно
		wnd = new Frame();
		//Отключаем возможность изменения размеров окна
		wnd.setResizable(false);
		//устанавливаем размеры в полный экран 
		wnd.setBounds(0, 0, w, h);
		//Устанавливаем цвет фона красный
		wnd.setBackground(Color.RED);
		//размещаем окно сверху других окон
		wnd.setAlwaysOnTop(true);
		//убираем рамку окна
		wnd.setUndecorated(true);
		//делаем прозрачность 50%
		wnd.setOpacity(0.5f);
		
		//подлкючаем обработчик события 
		wnd.addWindowListener(new WindowAdapter() {
			// При сворачивании окна 
			public void windowIconified(WindowEvent arg0) {
			wnd.setExtendedState(Frame.MAXIMIZED_BOTH);
		}
	});	
		//выводим окно
		wnd.setVisible(true);
	    //открываем окно на полный экран
		
		//создаем таймер с задержкой в 10 милимекунд (100 раз в секунду)
		tm = new Timer(10,new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//выводим окно на передний план
				wnd.toFront();
				
			}
		});
		//запускаем таймер
		tm.start();
		}
	}	
}	
