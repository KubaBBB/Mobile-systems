
using System;
using System.Collections.ObjectModel;
using System.Linq;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace Xamarin_App
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class AddFlowerPage : ContentPage
    {
        ObservableCollection<Flower> _flowers;
        Flower _flowerToModify;

        public AddFlowerPage(Flower flower, ObservableCollection<Flower> flowers)
        {
            InitializeComponent();
            _flowers = flowers;
            _flowerToModify = flower;
        }

        protected override async void OnAppearing()
        {
            editorName.Text = _flowerToModify.Name;
            editorUrl.Text = _flowerToModify.ImageUrl;
        }

        public async void AddFlower_OnClicked(object sender, EventArgs e)
        {
            if (string.IsNullOrEmpty(editorUrl.Text) && string.IsNullOrEmpty(editorName.Text))
            {
                var newFlower = new Flower() { Name = editorName.Text, ImageUrl = editorUrl.Text };

                _flowers.Remove(_flowerToModify);
                _flowers.Add(newFlower);
                _flowers.OrderBy(x => x.Name);
            }
        }
    }
}