public class GuitarString 
{
	 public static	final	int SAMPLING_RATE	= 44100;
    public static final double ENERGY_DECAY_FACTOR = 0.994; //0.994
	 private	RingBuffer buffer; // ring	buffer
	 
	 // YOUR	OTHER	INSTANCE	VARIABLES HERE
    private int numTimesTic;

	 /** create	a guitar	string of the given frequency	*/
	 public GuitarString(double frequency)	{
		  // YOUR CODE	HERE
		  buffer	= new	RingBuffer((int)(SAMPLING_RATE /	frequency));
		  while(!buffer.isFull()) {
				buffer.enqueue(0.0);
		  }
	 }

	 /** create	a guitar	string with	size & initial	values given by the array */
	 public GuitarString(double[]	init)	{
		  // YOUR CODE	HERE
		  buffer	= new	RingBuffer(init.length);
		  for	(double d :	init)	{
				buffer.enqueue(d);
		  }
	 }
    
    public RingBuffer distortion(double gain, double mix) {
         double maxX = max(abs(buffer));
         double gainStuff = gain / maxX;
         RingBuffer q = multiply(buffer,gainStuff);
         
         RingBuffer signQ = sign(multiply(q,-1));
         RingBuffer z = multiply(multiply(signQ, subtract(exp(signQ),1)),1.1);
         RingBuffer y = add(divide(multiply(multiply(z,mix),maxX),max(abs(z))), multiply(buffer,1-mix));
         
         y = divide(multiply(y,maxX),max(abs(z)));
         return y;
    }
    
    public RingBuffer divide(RingBuffer b, double d) {
         RingBuffer n = new RingBuffer(b.size());
         double elementB;
         for (int i = 0; i < b.size(); i++) {
            elementB = b.dequeue();
            b.enqueue(elementB);
            n.enqueue(elementB / d);
         }
         return n;
    }
    
    public RingBuffer exp(RingBuffer b) {
         RingBuffer n = new RingBuffer(b.size());
         double element;
         for (int i = 0; i < b.size(); i++) {
            element = b.dequeue();
            b.enqueue(element);
            n.enqueue(Math.pow(Math.E,element));
         }
         return n;
    }
    
    public RingBuffer add(RingBuffer b, RingBuffer a) {
         RingBuffer n = new RingBuffer(b.size());
         double elementA = 0;
         double elementB = 0;
         for (int i = 0; i < b.size(); i++) {
            elementA = a.dequeue();
            elementB = b.dequeue();
            a.enqueue(elementA);
            b.enqueue(elementB);
            n.enqueue(elementA * elementB);
         }
         return n;
    } 
    
    public RingBuffer subtract(RingBuffer b, double d) {
         RingBuffer n = new RingBuffer(b.size());
         double element;
         for (int i = 0; i < b.size(); i++) {
            element = b.dequeue();
            b.enqueue(element);
            n.enqueue(d - element);
         }
         return n;
    }
    //https://ses.library.usyd.edu.au/bitstream/handle/2123/8228/lab_report1_Distortion_Koosha_Ahmadi.pdf?sequence=2&isAllowed=y
    public RingBuffer sign(RingBuffer b) {
         RingBuffer n = new RingBuffer(b.size());
         double element;
         for (int i = 0; i < b.size(); i++) {
            element = b.dequeue();
            b.enqueue(element);
            if (element == 0) {
               n.enqueue(0);
            } else if (element > 0) {
               n.enqueue(1);
            } else {
               n.enqueue(-1);
            }
         }
         return n;
    }
    
    public RingBuffer multiply(RingBuffer b, RingBuffer a) {
         RingBuffer n = new RingBuffer(b.size());
         double elementA = 0;
         double elementB = 0;
         for (int i = 0; i < b.size(); i++) {
            elementA = a.dequeue();
            elementB = b.dequeue();
            a.enqueue(elementA);
            b.enqueue(elementB);
            n.enqueue(elementA * elementB);
         }
         return n;
    }
    
    public RingBuffer multiply(RingBuffer b, double d) {
         RingBuffer n = new RingBuffer(b.size());
         double element;
         for (int i = 0; i < b.size(); i++) {
            element = b.dequeue();
            b.enqueue(element);
            n.enqueue(element * d);
         }
         return n;
    }
    
    public RingBuffer abs(RingBuffer b) {
      RingBuffer n = new RingBuffer(b.size());
      double element;
      for (int i = 0; i < b.size(); i++) {
         element = b.dequeue();
         b.enqueue(element);
         n.enqueue(Math.abs(element));
      }
      return n;
    }
    
    public double max(RingBuffer b) {
         double max = b.peek();
         double element = 0;
         for (int i = 0 ; i < b.size(); i++) {
            element = b.dequeue();
            b.enqueue(element);
            if (max < element) {
               max = element;
            }
         }
         return max;
    }
    

	 /** pluck the	guitar string by replacing	the buffer with white noise */
	 public void pluck()	{
		  // YOUR CODE	HERE
        
        double prev = 0;
        double random = 0;
        for (int i = 0; i < buffer.size(); i++) {
            buffer.dequeue();
            random = Math.random() - .5;
            buffer.enqueue( (random * 2 + prev) / 3.0);
            prev = (random * 2 + prev) / 3.0;
        }
        
        //buffer = distortion(1,1);
        /*
        for (int i = 0; i < buffer.size(); i++) {
            buffer.dequeue();
            buffer.enqueue(Math.random() - .5);
        }
        */
	 }

	 /** advance the simulation one time step	*/
	 public void tic() {
		  // YOUR CODE	HERE
        numTimesTic++;
        buffer.enqueue(((buffer.dequeue() + buffer.peek()) / 2.0) * ENERGY_DECAY_FACTOR);
	 }

	 /** return	the current	sample */
	 public double	sample()	{
		  // YOUR CODE	HERE

		  return	buffer.peek(); //REPLACE
	 }

	 /** return	number of times tic was	called */
	 public int	time() {
		  // YOUR CODE	HERE

		  return	numTimesTic;	//REPLACE
	 }

	 public static	void main(String[] args) {
		  int	N = 25;
		  double[] samples =	{ .2,	.4, .5, .3,	-.2, .4,	.3, .0, -.1, -.3 };	
		  GuitarString	testString = new GuitarString(samples);
		  for	(int i =	0;	i < N; i++)	{
				int t	= testString.time();
				double sample = testString.sample();
				System.out.printf("%6d %8.4f\n",	t,	sample);
				testString.tic();
		  }
		  /*
			* Your program	should produce	the following output	when the	main() 
			* method	runs (DON'T	WORRY	ABOUT	VERY MINOR DIFFERENCES,	E.G. OFF	BY	0.001):
					 0	  0.2000
				 1	  0.4000
				 2	  0.5000
				 3	  0.3000
				 4	 -0.2000
				 5	  0.4000
				 6	  0.3000
				 7	  0.0000
				 8	 -0.1000
				 9	 -0.3000
				10	  0.2988
				11	  0.4482
				12	  0.3984
				13	  0.0498
				14	  0.0996
				15	  0.3486
				16	  0.1494
				17	 -0.0498
				18	 -0.1992
				19	 -0.0006
				20	  0.3720
				21	  0.4216
				22	  0.2232
				23	  0.0744
				24	  0.2232
			*/
	 }
}
