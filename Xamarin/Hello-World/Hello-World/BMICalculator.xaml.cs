using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace Hello_World
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class BMICalculator : ContentPage
    {
        public BMICalculator()
        {
            InitializeComponent();
        }

        public void Calculate_OnClicked(Object sender, EventArgs e)
        {
            var img = this.FindByName<Image>("Img");

            try
            {
                var w = Double.Parse(this.FindByName<Editor>("EditorWeight").Text);
                var h = Double.Parse(this.FindByName<Editor>("EditorHeight").Text);

                var bmi = w / (h * h)*10000;

                if(bmi < 18.5)
                {
                    img.Source = ImageSource.FromFile("underweight.png");
                }
                else if(bmi > 18.5 && bmi < 24.9)
                {
                    img.Source = ImageSource.FromFile("healthy.png");
                }
                else if(bmi > 25 && bmi < 29.9)
                {
                    img.Source = ImageSource.FromFile("overweight.png");
                }
                else if(bmi > 30)
                {
                    img.Source = ImageSource.FromFile("obesity.png");
                }

                img.IsVisible = true;
            }
            catch (Exception ex)
            {
                img.IsVisible = false;
            }
        }
    }
}