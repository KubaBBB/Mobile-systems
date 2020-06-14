using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;

namespace Hello_World
{
    // Learn more about making custom code visible in the Xamarin.Forms previewer
    // by visiting https://aka.ms/xamarinforms-previewer
    [DesignTimeVisible(false)]
    public partial class MainPage : ContentPage
    {
        public MainPage()
        {
            InitializeComponent();
        }

        public async void ShowDialog(System.Object sender, System.EventArgs eventArgs)
        {
            var editor = this.FindByName<Editor>("editorLabel");
            string result = await DisplayPromptAsync("Moj pierwszy alert", editor.Text);
            Console.WriteLine($"Rezultat: {result}");
        }

        public async void RunHowManyFingers_OnClicked(Object sender, EventArgs e)
        {
            await Navigation.PushAsync(new HowManyFingers());
        }

        public async void BMICalculator_OnClicked(Object sender, EventArgs e)
        {
            await Navigation.PushAsync(new BMICalculator());
        }
    }
}
