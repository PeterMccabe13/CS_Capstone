# travel_search.py
# Menu-based travel search app with destination, budget, and keyword filters + save/load favorites

import json
import os

# Sample travel data with keywords
travel_data = [
    {"destination": "Amalfi Coast", "country": "Italy", "cost": 1800, "keywords": ["beach", "romantic", "scenic"]},
    {"destination": "Santorini", "country": "Greece", "cost": 2000, "keywords": ["island", "luxury", "sunset"]},
    {"destination": "Tokyo", "country": "Japan", "cost": 2200, "keywords": ["city", "tech", "culture"]},
    {"destination": "Paris", "country": "France", "cost": 1600, "keywords": ["romantic", "city", "art"]},
    {"destination": "New York City", "country": "USA", "cost": 1500, "keywords": ["city", "shopping", "broadway"]}
]

# Display the main menu
def show_menu():
    print("\n=== Travel Search Menu ===")
    print("1. Search by destination")
    print("2. Search by budget")
    print("3. Search by keyword")
    print("4. Multi-criteria search")
    print("5. View favorites")
    print("6. Exit")

# Print trip results and prompt to save
def print_results_with_save_option(results):
    if not results:
        print("No results found.")
        return

    print("\nMatching destinations:")
    for idx, r in enumerate(results, start=1):
        print(f"{idx}. {r['destination']} ({r['country']}) - ${r['cost']} | Keywords: {', '.join(r['keywords'])}")
    
    # Ask user if they want to save any
    save_input = input("Enter the number(s) of trips to save to favorites (comma-separated), or press Enter to skip: ").strip()
    if save_input:
        indexes = save_input.split(",")
        for index in indexes:
            try:
                i = int(index) - 1
                if 0 <= i < len(results):
                    save_to_favorites(results[i])
                    print(f"Saved: {results[i]['destination']}")
                else:
                    print(f"Invalid index: {index}")
            except ValueError:
                print(f"Invalid input: {index}")

# Save a trip to favorites.json
def save_to_favorites(trip):
    favorites = load_favorites()
    if trip not in favorites:
        favorites.append(trip)
        with open("favorites.json", "w") as f:
            json.dump(favorites, f, indent=4)

# Load saved trips
def load_favorites():
    if os.path.exists("favorites.json"):
        with open("favorites.json", "r") as f:
            return json.load(f)
    return []

# View saved trips
def view_favorites():
    favorites = load_favorites()
    if not favorites:
        print("No saved trips.")
        return
    print("\nYour Saved Favorite Destinations:")
    for r in favorites:
        print(f"{r['destination']} ({r['country']}) - ${r['cost']} | Keywords: {', '.join(r['keywords'])}")

# Search functions
def search_by_destination():
    name = input("Enter destination name: ").strip().lower()
    results = [place for place in travel_data if name in place["destination"].lower()]
    print_results_with_save_option(results)

def search_by_budget():
    try:
        budget = int(input("Enter your maximum budget: "))
    except ValueError:
        print("Please enter a valid number.")
        return
    results = [place for place in travel_data if place["cost"] <= budget]
    print_results_with_save_option(results)

def search_by_keyword():
    keyword = input("Enter a travel keyword (e.g., beach, romantic, city): ").strip().lower()
    results = [place for place in travel_data if keyword in [k.lower() for k in place.get("keywords", [])]]
    print_results_with_save_option(results)

def multi_criteria_search():
    name = input("Enter destination name (or leave blank): ").strip().lower()
    keyword = input("Enter keyword (or leave blank): ").strip().lower()
    try:
        budget_input = input("Enter max budget (or leave blank): ").strip()
        budget = int(budget_input) if budget_input else None
    except ValueError:
        print("Invalid budget input.")
        return

    results = travel_data
    if name:
        results = [r for r in results if name in r["destination"].lower()]
    if keyword:
        results = [r for r in results if keyword in [k.lower() for k in r.get("keywords", [])]]
    if budget is not None:
        results = [r for r in results if r["cost"] <= budget]

    print_results_with_save_option(results)

# Main loop
def main():
    while True:
        show_menu()
        choice = input("Choose an option (1-6): ")

        if choice == "1":
            search_by_destination()
        elif choice == "2":
            search_by_budget()
        elif choice == "3":
            search_by_keyword()
        elif choice == "4":
            multi_criteria_search()
        elif choice == "5":
            view_favorites()
        elif choice == "6":
            print("Exiting... Goodbye!")
            break
        else:
            print("Invalid choice. Try again.")

# Entry point
if __name__ == "__main__":
    main()






