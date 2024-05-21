import "./restaurantcard.css"
import { IsOpen } from "../Helpers/isOpen"
import { useNavigate } from "react-router-dom"

export default function RestaurantCard({ restaurant }) {
    const navigate = useNavigate()
    const hello = () => {
        navigate(`restaurant/${restaurant.name}/`)
    }
        
    return(
        <>
            <div className="card">
                    <div className="body">
                        <img src={restaurant.image} alt=""/>
                    </div>

                    <div className="card-footer">
                        <p onClick={hello}>
                            <span className="title">
                                {restaurant.name}
                            </span>
                        </p>
                        <p className="reviews">
                            2096 &nbsp; reviews
                        </p>
                        <p className="type">
                            {restaurant.type}
                        </p>
                        <p className="place">                        
                            <span className="location">
                                <img className="icon location-icon" src="./icons/location.png" alt=""/>
                                <span>{restaurant.address.city}</span>
                            </span>
                        </p>

                        <p className="open">
                            <span className="open-span">
                                {IsOpen(restaurant.startTime, restaurant.endTime) ? 
                                    `Open. Closes at ${restaurant.endTime}` :
                                    `Closed. Opens at ${restaurant.startTime}`
                                } &nbsp;&nbsp;&nbsp;
                            </span>
                        </p>

                    </div>
                </div>
        </>
    )
}