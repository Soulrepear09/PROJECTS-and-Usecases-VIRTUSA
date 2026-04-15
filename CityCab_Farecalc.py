Price_menu = {'Economy':10, 'Premium':15, 'SUV':25}
def CityCab_fare(km, vechile_type, hour):
    if vechile_type not in Price_menu:
        return "Service Unavaliable"
    rate = Price_menu[vechile_type]
    base_price = km*rate
    if hour >=17 and hour<=20:
        surge_charge = base_price * 1.5
        total_fare = base_price + surge_charge
    else:
        surge_charge = 0
        total_fare = base_price
    if km<=10:
        trip_type = "Short ride"
    elif km<=20:
        trip_type = "Medium ride"
    else:
        trip_type = "Long ride"

    print("----CityCab Price Recipt----")
    print("Vechile type  : ", vechile_type)
    print("Distance      : ", km ,"km")
    print("Time/Hour     : ", hour)
    print("Rate per Km   :Rs.", rate)
    print("------------------------------")
    print("Base price    :Rs.", base_price)
    print("Surge Charge  :Rs.", surge_charge)
    print("Trip category :", trip_type)
    print("----------------------------------")
    print("Total amount  :Rs.", total_fare)
    print("====================================")
    print("ThankYou for riding CityCab")
    print("====================================")

vechile_type = input("Enter vechile type (Economy / Premium / SUV): ")
km = float(input("Enter the distance in km: "))
hour = int(input("Enter hour: "))

CityCab_fare(km, vechile_type, hour)
