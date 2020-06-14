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
    public partial class HowManyFingers : ContentPage
    {
        public HowManyFingers()
        {
            InitializeComponent();
        }

        public async void CheckResults_OnClicked(Object sender, EventArgs e)
        {
            var editor = this.FindByName<Editor>("EditorLabel");
            var resultField = this.FindByName<Label>("Result");
            int inputNum = -1;
            try
            {
                inputNum = Int32.Parse(editor.Text);
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex);

                return;
            }
            if (inputNum < 1 || inputNum > 10)
            {
                resultField.Text = "Nie przestrzegasz zasad, wpisz liczbe od 1 do 10";
                resultField.TextColor = Color.Black;
                return;
            }

            var rand = new Random().Next(1, 10);

            if (rand == inputNum)
            {
                resultField.TextColor = Color.Green;
                resultField.Text = "Tak zgadles";
            }
            else
            {
                resultField.TextColor = Color.Red;
                resultField.Text = "Przykro mi, nie zgadles. Moja liczba to " + rand;
            }

            var imgField1 = this.FindByName<Image>("CubeImg1");
            var imgField2 = this.FindByName<Image>("CubeImg2");
            imgField2.IsVisible = false;
            imgField1.IsVisible = false;

            if (rand < 7)
            {
                imgField1.Source = ImageSource.FromFile($"dice{rand}.png");
            }
            else
            {
                int temp = rand - 6;
                imgField1.Source = ImageSource.FromFile("dice6.png");
                imgField2.Source = ImageSource.FromFile($"dice{temp}.png");

                imgField2.IsVisible = true;
            }
            imgField1.IsVisible = true;
        }
    }
}