using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.ComponentModel;
using System.Linq;
using Xamarin.Forms;

namespace Xamarin_App
{
    // Learn more about making custom code visible in the Xamarin.Forms previewer
    // by visiting https://aka.ms/xamarinforms-previewer
    [DesignTimeVisible(false)]
    public partial class MainPage : ContentPage
    {
        public ObservableCollection<Flower> Flowers { get; private set; }

        public MainPage()
        {
            InitializeComponent();

            Flowers = new ObservableCollection<Flower>();
            ListView flowersListView = this.FindByName<ListView>("flowersListView");
            //flowersListView.ItemTemplate( () => { DataTemplate()}
            flowersListView.ItemsSource = Flowers;

            Flowers.Add(new Flower()
            {
                Name = "Stokrotka",
            });

            Flowers.Add(new Flower()
            {
                Name = "Krokus",
            });

            Flowers.Add(new Flower()
            {
                Name = "Tulipan",
            });

            Flowers.Add(new Flower()
            {
                Name = "Goździk",
            });

            Flowers.Add(new Flower()
            {
                Name = "Hiacynt",
            });

            Flowers.Add(new Flower()
            {
                Name = "Niezapominajka",
            });
        }

        protected override async void OnAppearing()
        {
            flowersListView.SelectedItem = null;
        }

        public async void PushToWeatherPage_OnClicked(object sender, EventArgs e)
        {
            await Navigation.PushAsync(new WeatherPage());
        }

        public async void OnListViewItemTapped(object sender, ItemTappedEventArgs e)
        {
            var item = e.Item as Flower;
            string action = await DisplayActionSheet("How would you like to do modification?", "Cancel", null, "Popup", "Page");

            if (action == "Page")
            {
                await Navigation.PushAsync(new AddFlowerPage(item, this.Flowers));
            }
            else if (action == "Popup")
            {
                string newName = await DisplayPromptAsync($"Flower:{item.Name}, {item.ImageUrl}.", "Enter new name");
                string newUrl = await DisplayPromptAsync($"Flower:{item.Name}, {item.ImageUrl}.", "Enter new image url");

                if (string.IsNullOrEmpty(newName) && string.IsNullOrEmpty(newUrl))
                    return;
                else
                {
                    if (string.IsNullOrEmpty(newName))
                        newName = item.Name;

                    if (string.IsNullOrEmpty(newUrl))
                        newUrl = item.ImageUrl;

                    var newFlower = new Flower() { Name = newName, ImageUrl = newUrl };
                    Flowers.Remove(item);
                    Flowers.Add(newFlower);
                    Flowers.OrderBy(x => x.Name);
                }
            }
            flowersListView.SelectedItem = null;
        }
    }
}
