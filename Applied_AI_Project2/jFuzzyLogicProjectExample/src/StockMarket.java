import nrc.fuzzy.*;

import java.io.*;
public class StockMarket
{
	public StockMarket()
	{
		
	}
	
	public static void main(String[] argv) throws FuzzyException,IOException 
	  {
		LeftLinearFunction llf = new LeftLinearFunction();
		FuzzyValueVector fvv = null;
		
		// Price has terms Very Low, Low, Medium, High and Very High
				FuzzyVariable price = new FuzzyVariable("Price",0.0,1.0, "On a scale 1-10 Very Low to Very High");
				price.addTerm("VeryLow", new LFuzzySet(0.0,0.3,llf));
				price.addTerm("Low", new LFuzzySet(0.25,0.5,llf));
				price.addTerm("Medium", new LFuzzySet(0.4,0.6,llf));
				price.addTerm("High", new LFuzzySet(0.5,0.75,llf));
				price.addTerm("VeryHigh", new LFuzzySet(0.7,1.0,llf));
				
		// Moving Average has terms Negative, Zero and Positive
				FuzzyVariable MA = new FuzzyVariable("MA",0.0,1.0, "On a scale 1-10 Negative to Positive");
				MA.addTerm("Negative", new LFuzzySet(0.0,0.4,llf));
			    MA.addTerm("Zero", new LFuzzySet(0.3,0.7,llf));
			    MA.addTerm("Positive", new LFuzzySet(0.6,1.0,llf));
	  		
		// Trade has terms Sell Many, Sell Few, Do Not Trade, Buy Few and Buy Many
				FuzzyVariable trade = new FuzzyVariable("Trade",0.0,1.0, "On a scale 1-10 Sell Many to Buy Many");
				trade.addTerm("SellMany", new LFuzzySet(0.0,0.4,llf));
			    trade.addTerm("SellFew", new LFuzzySet(0.3,0.5,llf));
			    trade.addTerm("DoNotTrade", new LFuzzySet(0.4,0.6,llf));
				trade.addTerm("BuyFew", new LFuzzySet(0.5,0.7,llf));
			    trade.addTerm("BuyMany", new LFuzzySet(0.6,1.0,llf));
			     
      // Rules
	  // Rule-1: IF Price is Very High AND Moving Average is Negative, THEN Sell Many
			    FuzzyRule VeryHighPriceNegMA = new FuzzyRule();
			    VeryHighPriceNegMA.addAntecedent(new FuzzyValue(price,"VeryHigh"));
			    VeryHighPriceNegMA.addAntecedent(new FuzzyValue(MA,"Negative"));
			    VeryHighPriceNegMA.addConclusion(new FuzzyValue(trade,"SellMany"));

	 // Rule-2: IF Price is High AND Moving Average is Negative, THEN Sell Many
			    FuzzyRule HighPriceNegMA = new FuzzyRule();
			    HighPriceNegMA.addAntecedent(new FuzzyValue(price,"High"));
			    HighPriceNegMA.addAntecedent(new FuzzyValue(MA,"Negative"));
			    HighPriceNegMA.addConclusion(new FuzzyValue(trade,"SellMany"));
			    
	 // Rule-3: IF Price is Medium AND Moving Average is Negative, THEN Sell Few
			    FuzzyRule MediumPriceNegMA = new FuzzyRule();
			    MediumPriceNegMA.addAntecedent(new FuzzyValue(price,"Medium"));
			    MediumPriceNegMA.addAntecedent(new FuzzyValue(MA,"Negative"));
			    MediumPriceNegMA.addConclusion(new FuzzyValue(trade,"SellFew"));	
			    
	 // Rule-4: IF Price is Low AND Moving Average is Negative, THEN Buy Few
			    FuzzyRule LowPriceNegMA = new FuzzyRule();
			    LowPriceNegMA.addAntecedent(new FuzzyValue(price,"Low"));
			    LowPriceNegMA.addAntecedent(new FuzzyValue(MA,"Negative"));
			    LowPriceNegMA.addConclusion(new FuzzyValue(trade,"BuyFew"));			    		    
			      	      
	 // Rule-5: IF Price is Very Low AND Moving Average is Negative, THEN Buy Many
			    FuzzyRule VeryLowPriceNegMA = new FuzzyRule();
			    VeryLowPriceNegMA.addAntecedent(new FuzzyValue(price,"VeryLow"));
			    VeryLowPriceNegMA.addAntecedent(new FuzzyValue(MA,"Negative"));
			    VeryLowPriceNegMA.addConclusion(new FuzzyValue(trade,"BuyMany"));		    
			     
	 // Rule-6: IF Price is Very High AND Moving Average is Zero, THEN Sell Many
			    FuzzyRule VeryHighPriceZeroMA = new FuzzyRule();
			    VeryHighPriceZeroMA.addAntecedent(new FuzzyValue(price,"VeryHigh"));
			    VeryHighPriceZeroMA.addAntecedent(new FuzzyValue(MA,"Zero"));
			    VeryHighPriceZeroMA.addConclusion(new FuzzyValue(trade,"SellMany"));
			    
	 // Rule-7: IF Price is High AND Moving Average is Zero, THEN Sell Few
			    FuzzyRule HighPriceZeroMA = new FuzzyRule();
			    HighPriceZeroMA.addAntecedent(new FuzzyValue(price,"High"));
			    HighPriceZeroMA.addAntecedent(new FuzzyValue(MA,"Zero"));
			    HighPriceZeroMA.addConclusion(new FuzzyValue(trade,"SellFew"));
			    
	 // Rule-8: IF Price is Medium AND Moving Average is Zero, THEN Do Not Trade
			    FuzzyRule MediumPriceZeroMA = new FuzzyRule();
			    MediumPriceZeroMA.addAntecedent(new FuzzyValue(price,"Medium"));
			    MediumPriceZeroMA.addAntecedent(new FuzzyValue(MA,"Zero"));
			    MediumPriceZeroMA.addConclusion(new FuzzyValue(trade,"DoNotTrade"));
			    
	 // Rule-9: IF Price is Low AND Moving Average is Zero, THEN Buy Few
			    FuzzyRule LowPriceZeroMA = new FuzzyRule();
			    LowPriceZeroMA.addAntecedent(new FuzzyValue(price,"Low"));
			    LowPriceZeroMA.addAntecedent(new FuzzyValue(MA,"Zero"));
			    LowPriceZeroMA.addConclusion(new FuzzyValue(trade,"BuyFew"));

	 // Rule-10: IF Price is Very Low AND Moving Average is Zero, THEN Buy Many
			    FuzzyRule VeryLowPriceZeroMA = new FuzzyRule();
			    VeryLowPriceZeroMA.addAntecedent(new FuzzyValue(price,"VeryLow"));
			    VeryLowPriceZeroMA.addAntecedent(new FuzzyValue(MA,"Zero"));
			    VeryLowPriceZeroMA.addConclusion(new FuzzyValue(trade,"BuyMany"));

	 // Rule-11: IF Price is Very High AND Moving Average is Positive, THEN Do Not Trade
			    FuzzyRule VeryHighPricePosMA = new FuzzyRule();
			    VeryHighPricePosMA.addAntecedent(new FuzzyValue(price,"VeryHigh"));
			    VeryHighPricePosMA.addAntecedent(new FuzzyValue(MA,"Positive"));
			    VeryHighPricePosMA.addConclusion(new FuzzyValue(trade,"DoNotTrade"));
			    
	 // Rule-12: IF Price is High AND Moving Average is Positive, THEN Do Not Trade
			    FuzzyRule HighPricePosMA = new FuzzyRule();
			    HighPricePosMA.addAntecedent(new FuzzyValue(price,"High"));
			    HighPricePosMA.addAntecedent(new FuzzyValue(MA,"Positive"));
			    HighPricePosMA.addConclusion(new FuzzyValue(trade,"DoNotTrade"));
			    
	 // Rule-13: IF Price is Medium AND Moving Average is Positive, THEN Do Not Trade
			    FuzzyRule MediumPricePosMA = new FuzzyRule();
			    MediumPricePosMA.addAntecedent(new FuzzyValue(price,"Medium"));
			    MediumPricePosMA.addAntecedent(new FuzzyValue(MA,"Positive"));
			    MediumPricePosMA.addConclusion(new FuzzyValue(trade,"DoNotTrade"));

	 // Rule-14: IF Price is Low AND Moving Average is Positive, THEN Buy Many
			    FuzzyRule LowPricePosMA = new FuzzyRule();
			    LowPricePosMA.addAntecedent(new FuzzyValue(price,"Low"));
			    LowPricePosMA.addAntecedent(new FuzzyValue(MA,"Positive"));
			    LowPricePosMA.addConclusion(new FuzzyValue(trade,"BuyMany"));    
			    
	 // Rule-15: IF Price is Very Low AND Moving Average is Positive, THEN Buy Few
			    FuzzyRule VeryLowPricePosMA = new FuzzyRule();
			    VeryLowPricePosMA.addAntecedent(new FuzzyValue(price,"Low"));
			    VeryLowPricePosMA.addAntecedent(new FuzzyValue(MA,"Positive"));
			    VeryLowPricePosMA.addConclusion(new FuzzyValue(trade,"BuyFew"));
			    
	 // Input Variables
			    double p,ma;
			    BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
			    System.out.println("Enter how the Price of stock is on a scale 0-1 (0-Very Low 1-Very High): ");
			    p=Double.parseDouble(br.readLine());
			    System.out.println("Enter how the Moving Average of stock is on a scale 0-1 (0-Negative 1-Positive):");
			    ma=Double.parseDouble(br.readLine());
			    
			    FuzzyValue inputPrice =  new FuzzyValue(price, new TriangleFuzzySet(p-0.05, 
		                p, p+0.05));
			    FuzzyValue inputMA =  new FuzzyValue(MA, new TriangleFuzzySet(ma-0.05, 
		                ma, ma+0.05));
			    FuzzyValue gtrade=null; 
			    
	 // Fire Rules
			    VeryHighPriceNegMA.removeAllInputs();
			    VeryHighPriceNegMA.addInput(inputPrice);
			    VeryHighPriceNegMA.addInput(inputMA);
			    HighPriceNegMA.removeAllInputs();
			    HighPriceNegMA.addInput(inputPrice);
			    HighPriceNegMA.addInput(inputMA);		    
			    MediumPriceNegMA.removeAllInputs();
			    MediumPriceNegMA.addInput(inputPrice);
			    MediumPriceNegMA.addInput(inputMA);
			    LowPriceNegMA.removeAllInputs();
			    LowPriceNegMA.addInput(inputPrice);
			    LowPriceNegMA.addInput(inputMA);
			    VeryLowPriceNegMA.removeAllInputs();
			    VeryLowPriceNegMA.addInput(inputPrice);
			    VeryLowPriceNegMA.addInput(inputMA);
			    
			    VeryHighPriceZeroMA.removeAllInputs();
			    VeryHighPriceZeroMA.addInput(inputPrice);
			    VeryHighPriceZeroMA.addInput(inputMA);
			    HighPriceZeroMA.removeAllInputs();
			    HighPriceZeroMA.addInput(inputPrice);
			    HighPriceZeroMA.addInput(inputMA);		    
			    MediumPriceZeroMA.removeAllInputs();
			    MediumPriceZeroMA.addInput(inputPrice);
			    MediumPriceZeroMA.addInput(inputMA);
			    LowPriceZeroMA.removeAllInputs();
			    LowPriceZeroMA.addInput(inputPrice);
			    LowPriceZeroMA.addInput(inputMA);
			    VeryLowPriceZeroMA.removeAllInputs();
			    VeryLowPriceZeroMA.addInput(inputPrice);
			    VeryLowPriceZeroMA.addInput(inputMA);
			    
			    VeryHighPricePosMA.removeAllInputs();
			    VeryHighPricePosMA.addInput(inputPrice);
			    VeryHighPricePosMA.addInput(inputMA);
			    HighPricePosMA.removeAllInputs();
			    HighPricePosMA.addInput(inputPrice);
			    HighPricePosMA.addInput(inputMA);		    
			    MediumPricePosMA.removeAllInputs();
			    MediumPricePosMA.addInput(inputPrice);
			    MediumPricePosMA.addInput(inputMA);
			    LowPricePosMA.removeAllInputs();
			    LowPricePosMA.addInput(inputPrice);
			    LowPricePosMA.addInput(inputMA);
			    VeryLowPricePosMA.removeAllInputs();
			    VeryLowPricePosMA.addInput(inputPrice);
			    VeryLowPricePosMA.addInput(inputMA);
			    
	// Rule Matching Conditions and Fuzzy Unions
			    if(VeryHighPriceNegMA.testRuleMatching())
			    {
			    	fvv=VeryHighPriceNegMA.execute();
			    	
			    	if(gtrade == null)
			    		gtrade = fvv.fuzzyValueAt(0);			    	
			    	else			    	
			    		gtrade = gtrade.fuzzyUnion(fvv.fuzzyValueAt(0));
			    	
			    	
			    }
			    
			    if(HighPriceNegMA.testRuleMatching())
			    {
			    	fvv=HighPriceNegMA.execute();
			    	
			    	if(gtrade == null)
			    		gtrade = fvv.fuzzyValueAt(0);			    	
			    	else			    	
			    		gtrade = gtrade.fuzzyUnion(fvv.fuzzyValueAt(0));
			    	
			    }
			    
			    if(MediumPriceNegMA.testRuleMatching())
			    {
			    	fvv=MediumPriceNegMA.execute();
			    	
			    	if(gtrade == null)
			    		gtrade = fvv.fuzzyValueAt(0);			    	
			    	else			    	
			    		gtrade = gtrade.fuzzyUnion(fvv.fuzzyValueAt(0));

			    }
			    
			    if(LowPriceNegMA.testRuleMatching())
			    {
			    	fvv=LowPriceNegMA.execute();
			    	
			    	if(gtrade == null)
			    		gtrade = fvv.fuzzyValueAt(0);			    	
			    	else			    	
			    		gtrade = gtrade.fuzzyUnion(fvv.fuzzyValueAt(0));
			    	
			    }
			    
			    if(VeryLowPriceNegMA.testRuleMatching())
			    {
			    	fvv=VeryLowPriceNegMA.execute();
			    	
			    	if(gtrade == null)
			    		gtrade = fvv.fuzzyValueAt(0);			    	
			    	else			    	
			    		gtrade = gtrade.fuzzyUnion(fvv.fuzzyValueAt(0));
			    	
			    }
			    
			    if(VeryHighPriceZeroMA.testRuleMatching())
			    {
			    	fvv=VeryHighPriceZeroMA.execute();
			    	
			    	if(gtrade == null)
			    		gtrade = fvv.fuzzyValueAt(0);			    	
			    	else			    	
			    		gtrade = gtrade.fuzzyUnion(fvv.fuzzyValueAt(0));

			    }
			    
			    if(HighPriceZeroMA.testRuleMatching())
			    {
			    	fvv=HighPriceZeroMA.execute();
			    	
			    	if(gtrade == null)
			    		gtrade = fvv.fuzzyValueAt(0);			    	
			    	else			    	
			    		gtrade = gtrade.fuzzyUnion(fvv.fuzzyValueAt(0));

			    }
			    
			    if(MediumPriceZeroMA.testRuleMatching())
			    {
			    	fvv=MediumPriceZeroMA.execute();
			    	
			    	if(gtrade == null)
			    		gtrade = fvv.fuzzyValueAt(0);			    	
			    	else			    	
			    		gtrade = gtrade.fuzzyUnion(fvv.fuzzyValueAt(0));

			    }
			    
			    if(LowPriceZeroMA.testRuleMatching())
			    {
			    	fvv=LowPriceZeroMA.execute();
			    	
			    	if(gtrade == null)
			    		gtrade = fvv.fuzzyValueAt(0);			    	
			    	else			    	
			    		gtrade = gtrade.fuzzyUnion(fvv.fuzzyValueAt(0));
			    	
			    }
			    
			    if(VeryLowPriceZeroMA.testRuleMatching())
			    {
			    	fvv=VeryLowPriceZeroMA.execute();
			    	
			    	if(gtrade == null)
			    		gtrade = fvv.fuzzyValueAt(0);			    	
			    	else			    	
			    		gtrade = gtrade.fuzzyUnion(fvv.fuzzyValueAt(0));
			    	
			    }
			    
			    if(VeryHighPricePosMA.testRuleMatching())
			    {
			    	fvv=VeryHighPricePosMA.execute();
			    	
			    	if(gtrade == null)
			    		gtrade = fvv.fuzzyValueAt(0);			    	
			    	else			    	
			    		gtrade = gtrade.fuzzyUnion(fvv.fuzzyValueAt(0));
			    	
			    }
			    
			    if(HighPricePosMA.testRuleMatching())
			    {
			    	fvv=HighPricePosMA.execute();
			    	
			    	if(gtrade == null)
			    		gtrade = fvv.fuzzyValueAt(0);			    	
			    	else			    	
			    		gtrade = gtrade.fuzzyUnion(fvv.fuzzyValueAt(0));
			    	
			    }
			    
			    if(MediumPricePosMA.testRuleMatching())
			    {
			    	fvv=MediumPricePosMA.execute();
			    	
			    	if(gtrade == null)
			    		gtrade = fvv.fuzzyValueAt(0);			    	
			    	else			    	
			    		gtrade = gtrade.fuzzyUnion(fvv.fuzzyValueAt(0));
			    	
			    }
			    
			    if(LowPricePosMA.testRuleMatching())
			    {
			    	fvv=LowPricePosMA.execute();
			    	
			    	if(gtrade == null)
			    		gtrade = fvv.fuzzyValueAt(0);			    	
			    	else			    	
			    		gtrade = gtrade.fuzzyUnion(fvv.fuzzyValueAt(0));

			    }
			    
			    if(VeryLowPricePosMA.testRuleMatching())
			    {
			    	fvv=VeryLowPricePosMA.execute();
			    	
			    	if(gtrade == null)
			    		gtrade = fvv.fuzzyValueAt(0);			    	
			    	else			    	
			    		gtrade = gtrade.fuzzyUnion(fvv.fuzzyValueAt(0));
			   
			    }
			    
	  //Printing Output
			    System.out.println("Trade: "+gtrade.momentDefuzzify()+"on a scale 0 - 1");
			    Double result = gtrade.momentDefuzzify();
			    if(result>=0.0 && result<=0.3)
			    	gtrade.setLinguisticExpression("SellMany");
			    if(result>0.3 && result<=0.4)
			    	gtrade.setLinguisticExpression("SellManyOrSellFew");
			    if(result>0.4 && result<=0.5)
			    	gtrade.setLinguisticExpression("SellFewOrDoNotTrade");
			    if(result>0.5 && result<=0.6)
			    	gtrade.setLinguisticExpression("DoNotTradeOrBuyFew");
			    if(result>0.6 && result<=0.7)
			    	gtrade.setLinguisticExpression("BuyFewOrBuyMany");
			    if(result>0.7 && result<=1.0)
			    	gtrade.setLinguisticExpression("BuyMany");
			    System.out.println("The sensible decision would be: "+gtrade.getLinguisticExpression());
			      	    
	  }
}