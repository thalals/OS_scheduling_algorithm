package scheduling;

import java.awt.Color;
import java.awt.Paint;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.axis.SubCategoryAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.GroupedStackedBarRenderer;
import org.jfree.data.KeyToGroupMap;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.ui.StandardGradientPaintTransformer;


public class StackedGanttChart extends ApplicationFrame {//프레임 단위
	int size; //프로세스 개수
	ArrayList<Integer> squen = new ArrayList<Integer>(); // 전체 프로세스 순서 정보-색 입혀야 됨
	int countLapse=0;//타임랩스마냥 전체 분할된 프로세스를 가지고 있는 카운트랩스 변수
	int[] proCount;//각 큐별로 몇 개의 분할을 가지고 있는지 확인
	Paint[] paint;//페인트 색을 입히기 위해 사용하는 페인트 변수
	ChartPanel[] chartPanel = new ChartPanel[7];
	DefaultCategoryDataset[] dataset = new DefaultCategoryDataset[7];
    public StackedGanttChart(int size) {
        super("OS scheduling");
        this.size=size;//프로세스의 전체 수를 알아야 함
        proCount=new int[size+1];//0은 cpu, 다음부터 각 프로세스 대기큐 분할
        paint = new Color[size+1];//마지막 값에 투명도 0인 층을 부여.
    	for(int i=0;i<dataset.length;i++) dataset[i]=new DefaultCategoryDataset();
    }
    public JPanel insert(int datanum, ArrayList<Process> p) {
        JFreeChart chart = createChart(dataset[datanum], p);
        chartPanel[datanum] = new ChartPanel(chart);//패널의 생성
        chartPanel[datanum].setPreferredSize(new java.awt.Dimension(960, 240));
        for(int i=0;i<proCount.length;i++) {
        	proCount[i]=0;
        }
        countLapse=0;
        squen.clear();
        return chartPanel[datanum];
    }
    
    void ChangeContent(int datanum) {
    	setContentPane(chartPanel[datanum]);
    }
    //기본적으로 데이터셋에 큐 형식으로 하나씩 분할된 정보를 담는다.
    //나중 그룹화를 위해 이름의 통일화는 필수, 어떤 색을 사용할지에 대한 인덱스도 설정하여야 함(나중에 알려줌)
    //큐에서 임의로 설정한 이름 규칙을 이용하여 프로세스, CPU 별로 그룹화를 진행
    //색상이 입혀지는 것은 프로세스 단위가 아니라 분할 단위. 분할된 프로세스 별 만든 색 정보를 이용하여 프로세스 별로 예쁘게 도식화
    //->프로세스가 띄엄띄엄 있는 것 처럼 보이지만, 사실 그 사이에 안 보이는 투명한 분할 층이 있는 거다. 만드는데 죽는줄.
    public void createDatasetComplete(int lapse, int index, int datanum) {//cpu에서 프로세스 끝남
    	dataset[datanum].addValue(lapse, "CPU"+String.valueOf(proCount[0]), " ");
    	//CPU 기준으로 0부터 쌓이는 형식으로 만들어주기 위해 이름 제어
    	squen.add(index);
    	countLapse++;
    	proCount[0]++;
    }
    public void createDatasetC_Ready(int lapse, int index, int datanum) {//cpu에서 준비큐로
    	dataset[datanum].addValue(lapse, "CPU"+String.valueOf(proCount[0]), " ");//cpu에 할당
    	squen.add(index);
    	countLapse++;
    	proCount[0]++;
    	
    	dataset[datanum].addValue(lapse, String.valueOf(index)+"-"+String.valueOf(proCount[index+1]), " ");//준비큐에 빈 공간 할당
    	//첫 번째 프로세스 큐의 첫 번째 분할:0-0 / 두 번 째 분할:0-1 요런 식으로 저장됨
    	squen.add(size);//남은 공간 빈 색칠
    	countLapse++;
    	proCount[index+1]++;
    }
    public void createDatasetR_Ceady(int lapse, int index, int datanum) {//준비큐에서 cpu로
    	dataset[datanum].addValue(lapse, String.valueOf(index)+"-"+String.valueOf(proCount[index+1]), " ");
    	squen.add(index);;
    	countLapse++;
    	proCount[index+1]++;
    }
    public void createDatasetReady(int lapse, int index, int datanum) {//큐에서 준비큐로
    	dataset[datanum].addValue(lapse, String.valueOf(index)+"-"+String.valueOf(proCount[index+1]), " ");
    	squen.add(size);;
    	countLapse++;
    	proCount[index+1]++;
    }
    public void createDatasetCPUnot(int lapse, int datanum) {//큐에서 준비큐로
    	dataset[datanum].addValue(lapse, "CPU"+String.valueOf(proCount[0]), " ");
    	squen.add(size);;//남은 cpu 공간 빈 색칠
    	countLapse++;
    	proCount[0]++;
    }

    public JFreeChart createChart(final CategoryDataset dataset, ArrayList<Process> p) {

    for(int i=0;i<size;i++) {//랜덤으로 색을 만들어주는 코드
    		int x=(int)(Math.random()*255);
    		int y=(int)(Math.random()*255);
    		int z=(int)(Math.random()*255);
    		paint[i]=new Color(x, y, z);
    	}//프로세스 개수와 같은 랜덤 색 생성
    	paint[size]=new Color(0,0,0,0);
    	//페인트 배열의 가장 마지막은 투명한 색.
        final JFreeChart chart = ChartFactory.createStackedBarChart(
            "OS scheduling",  // chart title
            "Category",                  // domain axis label
            "Time Wasted",                     // range axis label
            dataset,                     // data
            PlotOrientation.HORIZONTAL,    // the plot orientation
            true,                        // legend
            true,                        // tooltips
            false                        // urls
        );
        
        GroupedStackedBarRenderer renderer = new GroupedStackedBarRenderer();
        KeyToGroupMap map = new KeyToGroupMap("G0");
        for(int i=0;i<size+1;i++)//맞추어준 이름과 맞게 그룹화시켜주는 코드
        	//CPU0~이 하나의 그룹화가 되고, 0-0~/1-0~느낌으로 하나의 그룹화가 되어야 한다.
        {
        	for(int i1=0;i1<proCount[i];i1++) {
        		if(i==0)
        			map.mapKeyToGroup("CPU"+String.valueOf(i1), "G0");
        		else
        			map.mapKeyToGroup(String.valueOf(i-1)+"-"+String.valueOf(i1), "G"+String.valueOf(i));
        	}
        	
        }

        renderer.setSeriesToGroupMap(map); 
        for(int i=0;i<countLapse;i++) {//각 프로세스에 색을 칠해보자
        	renderer.setSeriesPaint(i, paint[squen.get(i)]);
        }
        renderer.setItemMargin(0.1);
        renderer.setGradientPaintTransformer(
            new StandardGradientPaintTransformer(GradientPaintTransformType.HORIZONTAL)
        );
        
        SubCategoryAxis domainAxis = new SubCategoryAxis("Process");
        domainAxis.setCategoryMargin(0.1);//표 사이 간격
        domainAxis.addSubCategory("CPU");
        for(int i=0;i<size;i++) {
        	domainAxis.addSubCategory("ReadyQueue"+String.valueOf(i+1));
        }
        
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setDomainAxis(domainAxis);
        plot.setRenderer(renderer);
        plot.setFixedLegendItems(createLegendItems(p));
        return chart;
        
    }

    public LegendItemCollection createLegendItems(ArrayList<Process> p) {//서브 창에 색칠
        LegendItemCollection result = new LegendItemCollection();
        p.sort(Comparator.comparingInt(j -> j.Arrival_time));
        for(int i=0;i<size;i++) {
        	LegendItem item = new LegendItem(p.get(i).ID, paint[i]);
        	result.add(item);
        }
        return result;
    }
}