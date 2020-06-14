namespace Xamarin_App
{
    public class Flower
    {
        public string Name { get; set; }
        public string ImageUrl { get; set; }

        public Flower()
        {
            ImageUrl = @"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTl-a6a_73FxuyYlRfXFIFIKJKHkE7_D5KjwRM-osWt92QmYd5w&usqp=CAU";
        }

        public override string ToString()
        {
            return Name + " " + ImageUrl;
        }
    }
}
