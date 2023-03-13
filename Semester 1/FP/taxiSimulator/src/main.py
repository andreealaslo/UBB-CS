from src.repository.repository import Repository
from src.service.rideService import RideService
from src.service.taxiService import TaxiService
from src.ui.ui import UI


def start():
    taxi_repo = Repository()
    taxi_service = TaxiService(taxi_repo)
    ride_service = RideService(taxi_repo)
    ui = UI(taxi_service, ride_service)
    ui.start()


start()
