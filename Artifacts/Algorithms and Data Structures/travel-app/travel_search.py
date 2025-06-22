# travel_search.py
# Menu-based travel search app with destination, budget, and keyword filters

# Sample travel data with keywords
travel_data = [
    {"destination": "Amalfi Coast", "country": "Italy", "cost": 1800, "keywords": ["beach", "romantic", "scenic"]},
    {"destination": "Santorini", "country": "Greece", "cost": 2000, "keywords": ["island", "luxury", "sunset"]},
    {"destination": "Tokyo", "country": "Japan", "cost": 2200, "keywords": ["city", "tech", "culture"]},
    {"destination": "Paris", "country": "France", "cost": 1600, "keywords": ["romantic", "city", "art"]},
    {"destination": "New York City", "country": "USA", "cost": 1500, "keywords": ["city", "shopping", "broadway"]}
]

# Displays the main menu options to the user
def show_menu():
    print("\n=== Travel Search Menu ===")
    print("1. Search by destination")
    print("2. Search by budget")
    print("3. Search by keyword")
    print("4. Multi-criteria search")
    print("5. Exit")

# Handles destination search based on user input
def search_by_destination():
    name = input("Enter destination name: ").strip().lower()
    results = [place for place in travel_data if name in place["destination"].lower()]
    print_results(results)

# Handles budget-based search for destinations within user’s price range
def search_by_budget():
    try:
        budget = int(input("Enter your maximum budget: "))
    except ValueError:
        print("Please enter a valid number.")
        return
    results = [place for place in travel_data if place["cost"] <= budget]
    print_results(results)

# Handles keyword-based search
def search_by_keyword():
    keyword = input("Enter a travel keyword (e.g., beach, romantic, city): ").strip().lower()
    results = [place for place in travel_data if keyword in [k.lower() for k in place.get("keywords", [])]]
    print_results(results)

# Multi-criteria filter: destination + budget + keyword
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

    print_results(results)

# Prints the list of results or a message if no results found
def print_results(results):
    if results:
        print("\nMatching destinations:")
        for r in results:
            print(f"{r['destination']} ({r['country']}) - ${r['cost']} | Keywords: {', '.join(r['keywords'])}")
    else:
        print("No results found.")

# Main program loop
def main():
    while True:
        show_menu()
        choice = input("Choose an option (1-5): ")

        if choice == "1":
            search_by_destination()
        elif choice == "2":
            search_by_budget()
        elif choice == "3":
            search_by_keyword()
        elif choice == "4":
            multi_criteria_search()
        elif choice == "5":
            print("Exiting... Goodbye!")
            break
        else:
            print("Invalid choice. Try again.")

# Entry point for the program
if __name__ == "__main__":
    main()



